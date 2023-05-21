package ru.msm.pm.dto.employee;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class EditEmployeeDto {

    private BigDecimal id;
    private Map<String, Object> fields;

}
