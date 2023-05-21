package ru.msm.pm.dto.employee;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.enums.EmployeeStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeDto {

    private BigDecimal id;
    private String displayName; // name surname [patronymic]
    private EmployeeStatus status;
    private String details; //необязательные поля (кроме patronymic)

}
