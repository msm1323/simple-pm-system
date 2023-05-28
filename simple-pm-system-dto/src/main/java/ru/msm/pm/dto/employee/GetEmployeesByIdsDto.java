package ru.msm.pm.dto.employee;

import lombok.Data;

import java.util.List;

@Data
public class GetEmployeesByIdsDto {

    List<Long> ids;        //обязательное для выполнения запроса поле

}
