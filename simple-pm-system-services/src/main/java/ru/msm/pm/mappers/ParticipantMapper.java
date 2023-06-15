package ru.msm.pm.mappers;

import org.springframework.stereotype.Component;
import ru.msm.pm.dto.projectTeam.AddParticipantDto;
import ru.msm.pm.dto.projectTeam.ParticipantDto;
import ru.msm.pm.model.Employee;
import ru.msm.pm.model.Participant;
import ru.msm.pm.model.Project;

@Component
public class ParticipantMapper {

    public Participant create(AddParticipantDto dto, Employee employee, Project project) {
        return Participant.builder()
                .employee(employee)
                .project(project)
                .role(dto.getRole())
                .build();
    }

    public ParticipantDto map(Participant participant) {
        String description = participant.getEmployee().getId() + " " + participant.getRole() + " " +
                participant.getProject().getId();
        return ParticipantDto.builder().participantId(participant.getId()).description(description).build();
    }

}
