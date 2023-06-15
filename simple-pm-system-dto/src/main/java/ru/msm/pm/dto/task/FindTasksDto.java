package ru.msm.pm.dto.task;

import lombok.*;
import ru.msm.pm.common.enums.TaskStatus;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindTasksDto {

    private String value;
    private TaskStatus[] statuses;
    private Long executorId;
    private Long authorId;
    private Date[] dateCreatedPeriod;
    private Date[] deadlinePeriod;

}
