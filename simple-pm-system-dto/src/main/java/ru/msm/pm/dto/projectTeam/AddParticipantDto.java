package ru.msm.pm.dto.projectTeam;

import lombok.*;
import ru.msm.pm.common.enums.ProjectRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddParticipantDto {
    //обязательные для выполнения запроса поля:
    private Long employeeId;
    private ProjectRole role;
    private Long projectId;

}
