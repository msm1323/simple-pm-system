package ru.msm.pm.dto.projectTeam;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.dto.employee.EmployeeDto;
import ru.msm.pm.dto.project.ProjectDto;
import ru.msm.pm.enums.ProjectRole;

@Getter
@Setter
public class ParticipantDto {

    private EmployeeDto participant;
    private ProjectRole role;
    private ProjectDto project;

}
