package ru.msm.pm.dto.task;

import lombok.*;
import ru.msm.pm.common.enums.TaskStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskDto {

    private Long id;
    private String name;
    private Long authorId;
    private Long projectId;
    private TaskStatus status;
    private String details; // все остальное

}
