package ru.msm.pm.services;

import ru.msm.pm.dto.project.GetProjectByIdDto;
import ru.msm.pm.dto.projectTeam.*;
import ru.msm.pm.model.Participant;

import java.util.List;

public interface ParticipantService {

    ParticipantDto getParticipantById(GetParticipantByIdDto dto);

    ParticipantDto createParticipant(AddParticipantDto dto);

    ParticipantDto deleteParticipantById(DeleteParticipantByIdDto dto);

    List<ParticipantDto> getParticipantsByProjectId(GetProjectTeamDto dto);

    List<ParticipantDto> getParticipantsByEmployeeId(Long id);

}
