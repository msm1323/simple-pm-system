package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.msm.pm.common.enums.EmployeeStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "Сотрудник", description = "Сотрудник компании")
public class EmployeeDto {

    @Schema(title = "Идентификатор")
    private Long id;

    @Schema(title = "ФИО")
    private String displayName; // name surname [patronymic]

    @Schema(title = "Статус")
    private EmployeeStatus status;

    @Schema(title = "Дополнительная информация о сотруднике")
    private String details; //необязательные поля (кроме patronymic)

    @Schema(title = "Пароль")//ограничить вывод
    private String password;

}
