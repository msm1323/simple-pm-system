package ru.msm.pm.mappers;

import org.springframework.stereotype.Component;
import ru.msm.pm.common.enums.EmployeeStatus;
import ru.msm.pm.dto.employee.*;
import ru.msm.pm.model.Employee;

@Component
public class EmployeeMapper {   //todo передаются некорректные объекты модели без части полей

    public Employee create(CreateEmployeeDto dto) {
        return new Employee(dto.getName(), dto.getSurname());
    }

    public Employee createFull(CreateFullEmployeeDto dto) {
        return Employee.builder()
                .status(EmployeeStatus.ACTIVE)
                .name(dto.getName())
                .surname(dto.getSurname())
                .patronymic(dto.getPatronymic())
                .position(dto.getPosition())
                .account(dto.getAccount())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

    public Employee setCredentials(SetEmployeeCredentials dto){
        return Employee.builder()
                .id(dto.getId())
                .account(dto.getAccount())
                .password(dto.getPassword())
                .build();
    }

    public Employee edit(UpdateEmployeeDto dto) {
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

    public EmployeeDto map(Employee employee) {
        String displayName = String.format("%s %s %s",
                employee.getSurname(), employee.getName(),
                (employee.getPatronymic() == null ? "" : employee.getPatronymic()));
        String details = String.format("%s%s",
                (employee.getPosition() == null ? "" : employee.getPosition()),
                (employee.getEmail() == null ? "" : " " + employee.getEmail()));
        return EmployeeDto.builder()
                .id(employee.getId())
                .status(employee.getStatus())
                .account(employee.getAccount())
                .password(employee.getPassword())
                .displayName(displayName)
                .details(details)
                .build();
    }

}
