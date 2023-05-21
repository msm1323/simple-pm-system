package ru.msm.pm.dto.task;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.enums.ProjectStatus;

import java.util.Date;

@Getter
@Setter
public class FindTaskDto {

    private String value;
    private ProjectStatus[] statuses;
    private Long[] executors;
    private Long[] authors;
    private Date[] dateCreatedPeriod;
    private Date[] deadlinePeriod;

}
