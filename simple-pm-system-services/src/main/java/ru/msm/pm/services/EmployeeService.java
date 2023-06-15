package ru.msm.pm.services;

import ru.msm.pm.dto.employee.*;

import java.util.List;

public interface EmployeeService {

    EmployeeDto create(CreateEmployeeDto dto);

    EmployeeDto createFull(CreateFullEmployeeDto dto);

    //задать пароль/логин удаленному сотруднику нельзя
    EmployeeDto setCredentials(SetEmployeeCredentials dto);

    EmployeeDto deleteEmployee(DeleteEmployeeDto dto);

    EmployeeDto editEmployee(UpdateEmployeeDto dto);

    List<EmployeeDto> findByValue(FindEmployeesDto dto);

    EmployeeDto getEmployeeById(GetEmployeeByIdDto dto);

    EmployeeDto getEmployeeByAccount(GetEmployeeByAccountDto dto);

    List<EmployeeDto> getEmployeesByIds(GetEmployeesByIdsDto dto);

    List<EmployeeDto> getAllEmployees();
}
