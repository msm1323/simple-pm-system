package ru.msm.pm.dto.task;

import lombok.Builder;
import lombok.Data;
import ru.msm.pm.common.enums.TaskStatus;

@Data
@Builder
public class TaskDto {

    private Long id;
    private String name;
    private Long authorId;
    private String projectCodeName;
    private TaskStatus status;
    private String details; // все остальное

}
