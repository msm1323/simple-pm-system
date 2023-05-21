package ru.msm.pm.dto.project;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.enums.ProjectStatus;

@Getter
@Setter
public class FindProjectsDto {

    private String value;
    private ProjectStatus[] statuses;

}
