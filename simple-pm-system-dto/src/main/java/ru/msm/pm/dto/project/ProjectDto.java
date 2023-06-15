package ru.msm.pm.dto.project;

import lombok.*;
import ru.msm.pm.common.enums.ProjectStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDto {

    //обязательные:
    private Long id;
    private String name;
    private String codeName;    //Является обязательным и уникальным среди всех проектов
    private ProjectStatus status;

    //необязательные:
    private String description;

}
