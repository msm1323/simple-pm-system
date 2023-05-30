package ru.msm.pm.dto.employee;

import lombok.Data;

@Data
public class FindEmployeeDto {

    //обязательное для выполнения запроса поле
    private String value;        //проверяется по атрибутам Фамилия, Имя, Отчество, учетной записи, адресу электронной почты и только среди активных сотрудников.

}
