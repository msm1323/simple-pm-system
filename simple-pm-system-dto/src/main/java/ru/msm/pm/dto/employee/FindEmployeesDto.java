package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "Поиск сотрудников", description = "Поиск сотрудников производится по текстовому значению, которое проверяется" +
        " по атрибутам Фамилия, Имя, Отчество, учетной записи, адресу электронной почты и только среди активных сотрудников.")
public class FindEmployeesDto {

    //обязательное для выполнения запроса поле
    @Schema(title = "Текстовое значение для поиска")
    private String value;        //проверяется по атрибутам Фамилия, Имя, Отчество, учетной записи, адресу электронной почты и только среди активных сотрудников.

}
