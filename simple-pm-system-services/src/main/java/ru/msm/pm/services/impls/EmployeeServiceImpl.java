package ru.msm.pm.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.msm.pm.common.enums.EmployeeStatus;
import ru.msm.pm.dao.jpa.repos.EmployeeRepository;
import ru.msm.pm.dao.jpa.specs.EmployeeSpecification;
import ru.msm.pm.dto.employee.*;
import ru.msm.pm.mappers.EmployeeMapper;
import ru.msm.pm.model.Employee;
import ru.msm.pm.services.EmployeeService;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeMapper mapper;

    @Override
    public EmployeeDto create(CreateEmployeeDto dto) {
        Employee employee = mapper.create(dto);
        long id = employeeRepository.createEmployee(employee);
        employee.setId(id);
        return mapper.map(employee);
    }

    @Override
    public EmployeeDto createFull(CreateFullEmployeeDto dto) {
        Employee employee = mapper.createFull(dto);
        if (employeeRepository.getEmployeeByAccount(dto.getAccount()).isPresent()) {
            throw new IllegalArgumentException("An employee with account=" + dto.getAccount() + " already exists.");
        }
        long id = employeeRepository.createFullEmployee(employee);
        employee.setId(id);
        return mapper.map(employee);
    }

    //задать пароль/логин удаленному сотруднику нельзя
    @Override
    public EmployeeDto setCredentials(SetEmployeeCredentials dto) {
        if (getIfExistOrElseThrow(dto.getId()).getStatus().equals(EmployeeStatus.DELETED)) {
            throw new IllegalArgumentException("You cannot set credentials to a deleted employee.");
        }

        employeeRepository.getEmployeeByAccount(dto.getAccount()).ifPresent(e -> {
            if (!e.getId().equals(dto.getId())) {
                throw new IllegalArgumentException("An employee with this account=" + dto.getAccount() + " already exists.");
            }
        });

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Employee employee = mapper.setCredentials(dto);
        employee = employeeRepository.setCredentials(employee).orElseThrow();
        return mapper.map(employee);
    }

    @Override
    public EmployeeDto deleteEmployee(DeleteEmployeeDto dto) {
        getIfExistOrElseThrow(dto.getId());
        Employee employee = employeeRepository.deleteEmployeeById(dto.getId()).orElseThrow();
        return mapper.map(employee);
    }

    @Override
    public EmployeeDto editEmployee(UpdateEmployeeDto dto) {
        getIfExistOrElseThrow(dto.getId());
        if (dto.getAccount() != null) {
            employeeRepository.getEmployeeByAccount(dto.getAccount()).ifPresent(e -> {
                if (!e.getId().equals(dto.getId())) {
                    throw new IllegalArgumentException("An employee with this account=" + dto.getAccount() + " already exists.");
                }
            });
        } else if (dto.getPassword() != null) {
            throw new IllegalArgumentException("You cannot set password without account.");
        }
        if (dto.getPassword() != null) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        Employee employee = mapper.edit(dto);
        return mapper.map(employeeRepository.updateEmployee(employee).orElseThrow());
    }

    @Override
    public List<EmployeeDto> findByValue(FindEmployeesDto dto) {
        List<Employee> employees = employeeRepository.findAll(EmployeeSpecification
                .activeAndTextEqualValue(dto.getValue()));
        return employees.stream().map(mapper::map).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(GetEmployeeByIdDto dto) {
        Employee employee = getIfExistOrElseThrow(dto.getId());
        return mapper.map(employee);
    }

    @Override
    public EmployeeDto getEmployeeByAccount(GetEmployeeByAccountDto dto) {
        Employee employee = employeeRepository.getEmployeeByAccount(dto.getAccount()).orElseThrow(() ->
                new NoSuchElementException("The employee with account=" + dto.getAccount() + " was not found."));
        return mapper.map(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeesByIds(GetEmployeesByIdsDto dto) {
        List<Long> ids = dto.getIds();
        List<Employee> employees = new ArrayList<>(ids.size());
        for (Long id : ids) {
            employeeRepository.getEmployeeById(id).ifPresent(employees::add);
        }
        return employees.stream().map(mapper::map).toList();
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(mapper::map).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.getEmployeeByAccount(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
        if (employee.getStatus().equals(EmployeeStatus.DELETED)) {
            throw new DisabledException("Employee was deleted!");
        }
        return new User(username, employee.getPassword(), Collections.emptyList());
    }

    private Employee getIfExistOrElseThrow(long id) {
        return employeeRepository.getEmployeeById(id)
                .orElseThrow(() -> new NoSuchElementException("The employee with id=" + id + " was not found."));
    }

    @PostConstruct
    public void initAdmin() {
        if (employeeRepository.getEmployeeByAccount("admin").isEmpty()) {
            Employee e = Employee.builder()
                    .name("Admin")
                    .surname("Adminoff")
                    .status(EmployeeStatus.ACTIVE)
                    .account("admin")
                    .password(passwordEncoder.encode("root"))
                    .build();
            employeeRepository.createFullEmployee(e);
        }
    }
}
