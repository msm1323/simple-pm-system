package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "Создание [полное]",
        description = "Создание карточки сотрудника с возможностью задать значения для всех полей сразу.")
public class CreateFullEmployeeDto {

    //обязательные:
    @Schema(title = "Имя")
    private String name;

    @Schema(title = "Фамилия")
    private String surname;

    //необязательные:
    @Schema(title = "Отчество")
    private String patronymic;

    @Schema(title = "Должность")
    private String position;

    @Schema(title = "Учетная запись")
    private String account;

    @Schema(title = "Пароль")
    private String password;

    @Schema(title = "Адрес электронной почты")
    private String email;

}
