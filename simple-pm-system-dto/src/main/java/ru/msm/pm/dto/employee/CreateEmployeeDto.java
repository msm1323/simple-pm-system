package ru.msm.pm.dto.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeDto {
    //обязательные для выполнения запроса поля:
    private String name;
    private String surname;

}
