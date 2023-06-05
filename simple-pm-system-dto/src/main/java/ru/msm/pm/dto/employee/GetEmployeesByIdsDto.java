package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(title = "Получение карточек сотрудников",
        description = "Получение карточек сотрудников по списку личных идентификаторов")
public class GetEmployeesByIdsDto {

    @Schema(title = "Список идентификаторов")
    List<Long> ids;        //обязательное для выполнения запроса поле

}
