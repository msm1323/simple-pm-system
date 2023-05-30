package ru.msm.pm.dto.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditEmployeeDto {

    //обязательные поля Employee:
    private Long id;        //обязательное для выполнения запроса поле
    private String name;
    private String surname;

    //необязательные:
    private String patronymic;
    private String position;
    private String account;
    private String email;

}
