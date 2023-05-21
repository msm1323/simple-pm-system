package ru.msm.pm.dto.project;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.enums.ProjectRole;
import ru.msm.pm.enums.ProjectStatus;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProjectDto {

    private String name;
    private String codeName;
    private ProjectStatus status;
    private String description;
    private List<Long> tasks;   // id
    private Map<Long, ProjectRole> team;    // (id, role)

}
