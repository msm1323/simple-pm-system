package ru.msm.pm.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetProjectByIdDto {   //todo убрать? не и используется

    @Schema(title = "Идентификатор")
    private Long id;        //обязательное для выполнения запроса поле

}