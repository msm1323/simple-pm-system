package ru.msm.pm.dto.employee;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteEmployeeDto {    //todo убрать?

    private Long id;        //обязательное для выполнения запроса поле

}
