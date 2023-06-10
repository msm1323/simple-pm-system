package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "Установка учетных данных",
        description = "Установка логина и пароля для сотрудника с соответствующим идентификатором.")
public class SetEmployeeCredentials {

    //обязательные поля:
    @Schema(title = "Идентификатор")
    private Long id;

    @Schema(title = "Учетная запись")
    private String account;

    @Schema(title = "Пароль")
    private String password;

}
