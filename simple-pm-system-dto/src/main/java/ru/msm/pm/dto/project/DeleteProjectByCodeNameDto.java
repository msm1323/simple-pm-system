package ru.msm.pm.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteProjectByCodeNameDto {

    @Schema(title = "Кодовое наименование проекта")
    private String codeName;        //обязательное для выполнения запроса поле

}
