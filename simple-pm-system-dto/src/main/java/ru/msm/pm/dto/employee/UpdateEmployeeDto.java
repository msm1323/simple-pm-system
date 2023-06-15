package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "Редактирование", description = "Редактирование карточки сотрудника")
public class UpdateEmployeeDto {

    //обязательные поля Employee:
    @Schema(title = "Идентификатор")
    private Long id;        //обязательное для выполнения запроса поле

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
