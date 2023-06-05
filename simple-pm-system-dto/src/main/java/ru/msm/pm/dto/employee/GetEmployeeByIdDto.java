package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetEmployeeByIdDto {   //todo убрать? не и используется

    @Schema(title = "Идентификатор")
    private Long id;        //обязательное для выполнения запроса поле

}
