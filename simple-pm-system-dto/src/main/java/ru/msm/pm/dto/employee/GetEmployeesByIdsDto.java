package ru.msm.pm.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetEmployeesByIdsDto {

    List<Long> ids;        //обязательное для выполнения запроса поле

}
