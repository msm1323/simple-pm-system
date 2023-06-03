package ru.msm.pm.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.msm.pm.dao.EmployeeRepository;
import ru.msm.pm.dao.EmployeeSpecification;
import ru.msm.pm.dto.employee.*;
import ru.msm.pm.mappers.EmployeeMapper;
import ru.msm.pm.model.Employee;
import ru.msm.pm.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

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
    public EmployeeDto edit(EditEmployeeDto dto) {
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
        Employee employee = employeeRepository.getEmployeeById(dto.getId());
        return EmployeeMapper.map(employee);
    }

    @Override
    public EmployeeDto getEmployeeByAccount(GetEmployeeByAccountDto dto) {
        Employee employee = employeeRepository.getEmployeeByAccount(dto.getAccount());
        return EmployeeMapper.map(employee);
    }

    @Override
    public List<EmployeeDto> getEmployeesByIds(GetEmployeesByIdsDto dto) {  //todo
        List<Long> ids = dto.getIds();
        List<Employee> employees = new ArrayList<>(ids.size());
        for (Long id : ids) {
            employees.add(employeeRepository.getEmployeeById(id));
        }
        return employees.stream().map(EmployeeMapper::map).toList();
    }
}
