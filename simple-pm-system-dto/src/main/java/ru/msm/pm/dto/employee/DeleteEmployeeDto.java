package ru.msm.pm.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeleteEmployeeDto {

    private Long id;        //обязательное для выполнения запроса поле

}
