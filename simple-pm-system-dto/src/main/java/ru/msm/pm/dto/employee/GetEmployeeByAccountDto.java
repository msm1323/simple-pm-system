package ru.msm.pm.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetEmployeeByAccountDto {

    private String account;        //обязательное для выполнения запроса поле

}
