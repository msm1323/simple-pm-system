package ru.msm.pm.dto.task;

import lombok.Builder;
import lombok.Data;
import ru.msm.pm.common.enums.ProjectStatus;

import java.util.Date;

@Data
@Builder
public class FindTaskDto {

    private String value;
    private ProjectStatus[] statuses;
    private Long[] executors;
    private Long[] authors;
    private Date[] dateCreatedPeriod;
    private Date[] deadlinePeriod;

}
