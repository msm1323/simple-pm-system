package ru.msm.pm.mappers;

import ru.msm.pm.dto.employee.CreateEmployeeDto;
import ru.msm.pm.dto.employee.DeleteEmployeeDto;
import ru.msm.pm.dto.employee.EditEmployeeDto;
import ru.msm.pm.dto.employee.EmployeeDto;
import ru.msm.pm.model.Employee;

public class EmployeeMapper {

    public static Employee create(CreateEmployeeDto dto) {
        return new Employee(dto.getName(), dto.getSurname());
    }

    public static Employee edit(EditEmployeeDto dto) {    //todo нет статуса
        return Employee.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .patronymic(dto.getPatronymic())
                .position(dto.getPosition())
                .account(dto.getAccount())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

    public static EmployeeDto map(Employee employee) {
        String displayName = String.format("%s %s %s",
                employee.getSurname(), employee.getName(),
                (employee.getPatronymic() == null ? "" : employee.getPatronymic()));
        String details = String.format("%s%s%s",
                (employee.getPosition() == null ? "" : employee.getPosition()),
                (employee.getAccount() == null ? "" : " " + employee.getAccount()),
                (employee.getEmail() == null ? "" : " " + employee.getEmail()));
        return EmployeeDto.builder()
                .id(employee.getId())
                .displayName(displayName)
                .status(employee.getStatus())
                .details(details)
                .password(employee.getPassword())
                .build();
    }

}
