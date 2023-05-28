package ru.msm.pm.dto.task;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EditTaskDto {

    private Long id;        //обязательное для выполнения запроса поле
    private String name;
    private Integer laborCosts;
    private Date deadline;
    private String description;
    private Long executorId;
}
