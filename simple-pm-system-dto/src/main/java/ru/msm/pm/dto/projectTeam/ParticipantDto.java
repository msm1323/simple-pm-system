package ru.msm.pm.dto.projectTeam;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantDto {

    private Long participantId;
    private String description; // employee id & role & project id

}
