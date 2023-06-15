package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "Создание [упрощенное]", description = "Упрощенное создание карточки сотрудника - только с обязательными полями.")
public class CreateEmployeeDto {
    //обязательные для выполнения запроса поля:
    @Schema(title = "Имя")
    private String name;

    @Schema(title = "Фамилия")
    private String surname;

}
