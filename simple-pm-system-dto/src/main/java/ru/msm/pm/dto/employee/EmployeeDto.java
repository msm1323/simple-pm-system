package ru.msm.pm.dto.employee;

import lombok.Builder;
import lombok.Data;
import ru.msm.pm.enums.EmployeeStatus;

@Data
@Builder
public class EmployeeDto {

    private Long id;
    private String displayName; // name surname [patronymic]
    private EmployeeStatus status;
    private String details; //необязательные поля (кроме patronymic)

}
