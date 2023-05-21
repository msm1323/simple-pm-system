package ru.msm.pm.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class EditTaskDto {

    private BigDecimal id;
    private Map<String, Object> fields;

}
