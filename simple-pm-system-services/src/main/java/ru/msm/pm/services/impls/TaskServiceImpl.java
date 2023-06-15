package ru.msm.pm.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.msm.pm.common.enums.EmployeeStatus;
import ru.msm.pm.common.enums.TaskStatus;
import ru.msm.pm.dao.jpa.repos.EmployeeRepository;
import ru.msm.pm.dao.jpa.repos.ParticipantRepository;
import ru.msm.pm.dao.jpa.repos.ProjectRepository;
import ru.msm.pm.dao.jpa.repos.TaskRepository;
import ru.msm.pm.dao.jpa.specs.TaskSpecification;
import ru.msm.pm.dto.task.*;
import ru.msm.pm.mappers.TaskMapper;
import ru.msm.pm.model.Employee;
import ru.msm.pm.model.Participant;
import ru.msm.pm.model.Project;
import ru.msm.pm.model.Task;
import ru.msm.pm.model.abstract_entities.BaseTask;
import ru.msm.pm.services.TaskService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    ProjectRepository projectRepository;
    EmployeeRepository employeeRepository;
    ParticipantRepository participantRepository;
    TaskMapper mapper;

    @Override
    public TaskDto getTaskById(GetTaskByIdDto dto) {
        Task task = getIfExistOrElseThrow(dto.getId());
        return mapper.map(task);
    }

    @Override
    public List<TaskDto> getTasksByProjectId(GetTasksByProjectIdDto dto) {
        getProjectIfExistOrElseThrow(dto.getProjectId());
        List<Task> tasks = taskRepository.getTasksByProjectId(dto.getProjectId(), Sort.by("dateCreated").descending());
        return tasks.stream().map(mapper::map).toList();
    }

    @Override
    public List<TaskDto> getTasksByFilter(FindTasksDto dto) {
        if (dto.getDeadlinePeriod().length != 2 || dto.getDateCreatedPeriod().length != 2) {
            throw new IllegalArgumentException("DeadlinePeriod and DateCreatedPeriod must be of length 2");
        }
        List<Task> tasks = taskRepository.findAll(TaskSpecification.findByFilter(dto.getValue(), dto.getStatuses(),
                dto.getExecutorId(), dto.getAuthorId(), dto.getDeadlinePeriod(), dto.getDateCreatedPeriod()));
        tasks.sort(new BaseTask.TaskDataCreatedComparator());
        return tasks.stream().map(mapper::map).toList();
    }

    @Override
    public TaskDto createFullTask(CreateFullTaskDto dto) {
        Project project = getProjectIfExistOrElseThrow(dto.getProjectId());
        List<Participant> participants = participantRepository.getParticipantsByProjectId(dto.getProjectId());
        Employee author = validateAuthor(participants);
        Employee executor = validateExecutor(participants, dto.getExecutorId());
        Date dateCreated = new Date();
        validateDeadline(dateCreated, dto.getLaborCosts(), dto.getDeadline());

        Task task = mapper.createFull(dto, project, author, executor);
        task.setId(taskRepository.createFullTask(task));
        return mapper.map(task);
    }

    @Override
    public TaskDto createTask(CreateTaskDto dto) {
        Project project = getProjectIfExistOrElseThrow(dto.getProjectId());
        List<Participant> participants = participantRepository.getParticipantsByProjectId(dto.getProjectId());
        Employee author = validateAuthor(participants);
        Date dateCreated = new Date();
        validateDeadline(dateCreated, dto.getLaborCosts(), dto.getDeadline());

        Task task = mapper.create(dto, project, author);
        task.setId(taskRepository.createTask(task));
        return mapper.map(task);
    }

    @Override
    public TaskDto updateFullTask(EditFullTaskDto dto) {
        Task dbTask = getIfExistOrElseThrow(dto.getId());
        List<Participant> participants = participantRepository.getParticipantsByProjectId(dbTask.getProject().getId());
        Employee author = validateAuthor(participants);
        Employee executor = validateExecutor(participants, dto.getExecutorId());
        validateDeadlineAndLaborCosts(dbTask.getDateCreated(), dto.getLaborCosts(), dto.getDeadline());

        Task task = mapper.editFull(dto, author, executor);
        task = taskRepository.updateFullTask(task).orElseThrow();
        return mapper.map(task);
    }

    @Override
    public TaskDto updateTask(EditTaskDto dto) {
        Task dbTask = getIfExistOrElseThrow(dto.getId());
        List<Participant> participants = participantRepository.getParticipantsByProjectId(dbTask.getProject().getId());
        Employee author = validateAuthor(participants);
        validateDeadlineAndLaborCosts(dbTask.getDateCreated(), dto.getLaborCosts(), dto.getDeadline());

        Task task = mapper.edit(dto, author);
        task = taskRepository.updateTask(task).orElseThrow();
        return mapper.map(task);
    }

    @Override
    public TaskDto updateTaskDescription(EditTaskDescriptionDto dto) {
        Task task = getIfExistOrElseThrow(dto.getId());
        List<Participant> participants = participantRepository.getParticipantsByProjectId(task.getProject().getId());
        Employee author = validateAuthor(participants);
        task.setDescription(dto.getDescription());
        task = taskRepository.updateTaskDescription(task).orElseThrow();
        return mapper.map(task);
    }

    @Override
    public TaskDto updateTaskStatus(UpdateTaskStatusDto dto) {
        Task task = getIfExistOrElseThrow(dto.getId());
        int i = task.getStatus().ordinal() + 1;
        int newStatusIndex = i >= TaskStatus.values().length ? TaskStatus.values().length - 1 : i;
        task = taskRepository.updateTaskStatus(task.getId(), TaskStatus.values()[newStatusIndex].name()).orElseThrow();
        return mapper.map(task);
    }

    private void validateDeadline(Date dateCreated, Integer laborCosts, Date deadline) {
        long minDeadlineMillis = dateCreated.getTime() + laborCosts * 3600000;
        Date minDeadline = new Date(minDeadlineMillis);
        if (deadline.before(minDeadline)) {
            throw new IllegalArgumentException("The deadline cannot be less than the date of creation of the task + labor costs." +
                    "\nThat is, not earlier than " + minDeadline + ".");
        }
    }

    private void validateDeadlineAndLaborCosts(Date dateCreated, Integer laborCosts, Date deadline) {
        if (deadline.getTime() <= dateCreated.getTime()) {
            throw new IllegalArgumentException("");
        }
        int maxLaborCosts = (int) ((deadline.getTime() - dateCreated.getTime()) / 3600000);
        if (laborCosts > maxLaborCosts) {
            throw new IllegalArgumentException("Labor costs cannot be more than " + maxLaborCosts + '!' +
                    " Change either the labor costs or the deadline. Remember, the deadline cannot be less than" +
                    " the creation date + labor costs");
        }
    }

    private Employee validateAuthor(List<Participant> participants) {
        String authorAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee author = employeeRepository.getEmployeeByAccount(authorAccount).orElseThrow();
        if (isNotProjectParticipant(participants, author.getId())) {
            throw new IllegalArgumentException("You are not a project participant! You cannot add a task to this project!");
        }
        return author;
    }

    private Employee validateExecutor(List<Participant> participants, Long executorId) {
        Employee executor = employeeRepository.getEmployeeById(executorId).orElseThrow(
                () -> new NoSuchElementException("The employee with id=" + executorId + " was not found."));
        if (isNotProjectParticipant(participants, executor.getId())) {
            throw new IllegalArgumentException("The employee with id=" + executor.getId() + " cannot be a task executor on this project!" +
                    "This employee is not a project participant.");
        }
        if (executor.getStatus().equals(EmployeeStatus.DELETED)) {
            throw new IllegalArgumentException("The employee with id=" + executor.getId() + " cannot be a task executor!" +
                    "This employee is deleted.");
        }
        return executor;
    }

    private boolean isNotProjectParticipant(List<Participant> participants, Long employeeId) {
        long l = participants.stream().map(p -> p.getEmployee().getId()).filter(id -> id.equals(employeeId)).count();
        return l != 0;
    }

    private Task getIfExistOrElseThrow(long id) {
        return taskRepository.getTaskById(id)
                .orElseThrow(() -> new NoSuchElementException("The task with id=" + id + " was not found."));
    }

    private Project getProjectIfExistOrElseThrow(long id) {
        return projectRepository.getProjectById(id)
                .orElseThrow(() -> new NoSuchElementException("The project with id=" + id + " was not found."));
    }

}
