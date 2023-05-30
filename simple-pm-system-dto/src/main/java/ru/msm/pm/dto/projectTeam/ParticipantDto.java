package ru.msm.pm.dto.projectTeam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantDto {

    private Long participantId;
    private String description; // employee id & role & project id

}
