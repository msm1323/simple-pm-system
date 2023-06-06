package ru.msm.pm.dto.projectTeam;

import lombok.Builder;
import lombok.Data;
import ru.msm.pm.common.enums.ProjectRole;

@Data
@Builder
public class AddParticipantDto {
    //обязательные для выполнения запроса поля:
    private Long employeeId;
    private ProjectRole role;
    private Long projectId;

}
