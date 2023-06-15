package ru.msm.pm.dto.projectTeam;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectTeamDto {

    private Long projectId;
    private List<ParticipantDto> team;

}
