package ru.msm.pm.dto.task;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.enums.TaskStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class TaskDto {

    private BigDecimal id;
    private String name;
    private Long authorId;
    private String projectCodeName;
    private TaskStatus status;
    private String details; // все остальное

}
