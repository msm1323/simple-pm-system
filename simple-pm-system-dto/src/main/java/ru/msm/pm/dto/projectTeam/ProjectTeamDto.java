package ru.msm.pm.dto.projectTeam;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectTeamDto {

    private Long projectId;
    private List<ParticipantDto> team;

}
