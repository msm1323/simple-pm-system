package ru.msm.pm.dto.projectTeam;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.dto.employee.EmployeeDto;
import ru.msm.pm.enums.ProjectRole;

import java.util.Map;

@Getter
@Setter
public class ProjectTeamDto {

    private String projectCodeName;
    private Map<EmployeeDto, ProjectRole> team;

}
