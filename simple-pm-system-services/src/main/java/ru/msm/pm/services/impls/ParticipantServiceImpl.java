package ru.msm.pm.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.msm.pm.dao.jpa.repos.EmployeeRepository;
import ru.msm.pm.dao.jpa.repos.ParticipantRepository;
import ru.msm.pm.dao.jpa.repos.ProjectRepository;
import ru.msm.pm.dao.jpa.repos.TaskRepository;
import ru.msm.pm.dto.projectTeam.*;
import ru.msm.pm.mappers.ParticipantMapper;
import ru.msm.pm.model.Employee;
import ru.msm.pm.model.Participant;
import ru.msm.pm.model.Project;
import ru.msm.pm.services.ParticipantService;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ParticipantServiceImpl implements ParticipantService {

    ParticipantRepository participantRepository;
    EmployeeRepository employeeRepository;
    ProjectRepository projectRepository;
    TaskRepository taskRepository;

    ParticipantMapper mapper;

    @Override
    public ParticipantDto getParticipantById(GetParticipantByIdDto dto) {
        Participant participant = participantRepository.getParticipantById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("The participant with id=" + dto.getId() + " was not found."));
        return mapper.map(participant);
    }

    @Override
    public ParticipantDto createParticipant(AddParticipantDto dto) {
        Employee employee = employeeRepository.getEmployeeById(dto.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("The employee with id=" + dto.getEmployeeId() + " was not found."));
        Project project = projectRepository.getProjectById(dto.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("The project with id=" + dto.getProjectId() + " was not found."));
        Participant participant = mapper.create(dto, employee, project);
        Long id = participantRepository.createParticipant(participant);
        participant.setId(id);
        return mapper.map(participant);
    }

    @Override
    public ParticipantDto deleteParticipantById(DeleteParticipantByIdDto dto) {
        Participant participant = participantRepository.deleteParticipantById(dto.getParticipantId())
                .orElseThrow(() -> new NoSuchElementException("The participant with id=" + dto.getParticipantId() + " was not found."));
        taskRepository.deleteTaskExecutor(participant.getEmployee().getId());
        return mapper.map(participant);
    }

    @Override
    public List<ParticipantDto> getParticipantsByProjectId(GetProjectTeamDto dto) {
        List<Participant> participants = participantRepository.getParticipantsByProjectId(dto.getProjectId());
        return participants.stream().map(mapper::map).toList();
    }

    @Override
    public List<ParticipantDto> getParticipantsByEmployeeId(Long id) {
        List<Participant> participants = participantRepository.getParticipantsByEmployee_Id(id);
        return participants.stream().map(mapper::map).toList();
    }
}
