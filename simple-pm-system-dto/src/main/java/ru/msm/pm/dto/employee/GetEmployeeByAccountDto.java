package ru.msm.pm.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetEmployeeByAccountDto {

    @Schema(title = "Учетная запись")
    private String account;        //обязательное для выполнения запроса поле

}
