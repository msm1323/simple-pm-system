package ru.msm.pm.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindEmployeesDto {

    //обязательное для выполнения запроса поле
    private String value;        //проверяется по атрибутам Фамилия, Имя, Отчество, учетной записи, адресу электронной почты и только среди активных сотрудников.

}
