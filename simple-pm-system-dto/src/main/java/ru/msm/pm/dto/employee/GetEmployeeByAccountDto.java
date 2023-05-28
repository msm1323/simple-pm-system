package ru.msm.pm.dto.employee;

import lombok.Data;

@Data
public class GetEmployeeByAccountDto {

    private String account;        //обязательное для выполнения запроса поле

}
