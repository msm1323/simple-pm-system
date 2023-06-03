package ru.msm.pm.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetEmployeeByIdDto {

    private Long id;        //обязательное для выполнения запроса поле

}
