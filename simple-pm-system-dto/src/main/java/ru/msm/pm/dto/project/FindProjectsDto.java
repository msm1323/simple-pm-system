package ru.msm.pm.dto.project;

import lombok.Data;
import ru.msm.pm.enums.ProjectStatus;

@Data
public class FindProjectsDto {

    private String value;
    private ProjectStatus[] statuses;

}
