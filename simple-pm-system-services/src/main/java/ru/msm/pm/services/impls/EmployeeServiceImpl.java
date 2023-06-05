package ru.msm.pm.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.msm.pm.dao.EmployeeRepository;
import ru.msm.pm.dao.EmployeeSpecification;
import ru.msm.pm.dto.employee.*;
import ru.msm.pm.mappers.EmployeeMapper;
import ru.msm.pm.model.Employee;
import ru.msm.pm.services.EmployeeService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDto create(CreateEmployeeDto dto) {
        Employee employee = EmployeeMapper.create(dto);
        long id = employeeRepository.createEmployee(employee);
        employee.setId(id);
        return EmployeeMapper.map(employee);
    }

    @Override
    public EmployeeDto delete(DeleteEmployeeDto dto) {
        Employee employee = employeeRepository.deleteEmployeeById(dto.getId());
        return EmployeeMapper.map(employee);
    }

    @Override
    public EmployeeDto edit(EditEmployeeDto dto) {  //todo нет проверки на null поля
        dto.setPassword(passwordEncoder.encode(dto.getPassword())); //todo
        Employee employee = EmployeeMapper.edit(dto);
        return EmployeeMapper.map(employeeRepository.updateEmployee(employee));
    }

    @Override
    public List<EmployeeDto> findByValue(FindEmployeesDto dto) {
        List<Employee> employees = employeeRepository.findAll(EmployeeSpecification
                .activeAndTextEqualValue(dto.getValue()));
        return employees.stream().map(EmployeeMapper::map).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(GetEmployeeByIdDto dto) {
        Employee employee = employeeRepository.getEmployeeById(dto.getId()).get();//todo
        return EmployeeMapper.map(employee);
    }

    @Override
    public EmployeeDto getEmployeeByAccount(GetEmployeeByAccountDto dto) {
        Employee employee = employeeRepository.getEmployeeByAccount(dto.getAccount()).get();//todo
        return EmployeeMapper.map(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeesByIds(GetEmployeesByIdsDto dto) {  //todo
        List<Long> ids = dto.getIds();
        List<Employee> employees = new ArrayList<>(ids.size());
        for (Long id : ids) {
            employees.add(employeeRepository.getEmployeeById(id).get());//todo
        }
        return employees.stream().map(EmployeeMapper::map).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.getEmployeeByAccount(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
        return new User(username, employee.getPassword(), Collections.emptyList());
    }

    @PostConstruct
    public void initAdmin() {        // todo
        if (employeeRepository.getEmployeeByAccount("admin").isEmpty()) {
            Employee e = new Employee("Admin", "Adminoff");
            long id = employeeRepository.createEmployee(e);
            e.setId(id);
            e.setAccount("admin");
            e.setPassword(passwordEncoder.encode("root"));
            employeeRepository.updateEmployee(e);
        }
    }
}
