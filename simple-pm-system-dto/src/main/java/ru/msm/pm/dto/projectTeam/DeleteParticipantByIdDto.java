package ru.msm.pm.dto.projectTeam;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteParticipantByIdDto {

    private Long participantId;        //обязательное для выполнения запроса поле

}
