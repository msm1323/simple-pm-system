package ru.msm.pm.dto.project;

import lombok.Builder;
import lombok.Data;
import ru.msm.pm.common.enums.ProjectStatus;

@Data
@Builder
public class ProjectDto {

    //обязательные:
    private Long id;
    private String name;
    private String codeName;    //Является обязательным и уникальным среди всех проектов
    private ProjectStatus status;

    //необязательные:
    private String description;

}
