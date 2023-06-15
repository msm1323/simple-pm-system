package ru.msm.pm.mappers;

import org.springframework.stereotype.Component;
import ru.msm.pm.common.enums.TaskStatus;
import ru.msm.pm.dto.task.*;
import ru.msm.pm.model.Employee;
import ru.msm.pm.model.Project;
import ru.msm.pm.model.Task;

import java.util.Date;

@Component
public class TaskMapper {

    public Task create(CreateTaskDto dto, Project project, Employee author) {
        Date nowDate = new Date();
        return Task.builder()
                .name(dto.getName())
                .project(project)
                .laborCosts(dto.getLaborCosts())
                .deadline(dto.getDeadline())
                //генерируемые
                .dateCreated(nowDate)
                .dateUpdated(nowDate)
                .author(author)
                .status(TaskStatus.NEW)
                .build();
    }

    public Task createFull(CreateFullTaskDto dto, Project project, Employee author, Employee executor) {
        Date nowDate = new Date();
        return Task.builder()
                .name(dto.getName())
                .project(project)
                .laborCosts(dto.getLaborCosts())
                .deadline(dto.getDeadline())
                .executor(executor)
                .description(dto.getDescription())
                //генерируемые
                .dateCreated(nowDate)
                .dateUpdated(nowDate)
                .author(author)
                .status(TaskStatus.NEW)
                .build();
    }

    public Task edit(EditTaskDto dto, Employee author) {
        Date nowDate = new Date();
        return Task.builder()
                .id(dto.getId())
                .name(dto.getName())
                .laborCosts(dto.getLaborCosts())
                .deadline(dto.getDeadline())
                //генерируемые
                .dateUpdated(nowDate)
                .author(author)
                .build();
    }

    public Task editFull(EditFullTaskDto dto, Employee author, Employee executor) {
        Date nowDate = new Date();
        return Task.builder()
                .id(dto.getId())
                .name(dto.getName())
                .laborCosts(dto.getLaborCosts())
                .deadline(dto.getDeadline())
                .description(dto.getDescription())
                .executor(executor)
                //генерируемые
                .dateUpdated(nowDate)
                .author(author)
                .build();
    }

    public TaskDto map(Task task) {
        String details = String.format("%s;%s;%s;%s;%s;%s;",
                "Deadline: " + task.getDeadline(),
                "Labor costs: " + task.getLaborCosts(),
                "Date updated: " + task.getDateUpdated(),
                "Date created: " + task.getDateCreated(),
                "Description: " + task.getDescription(),
                "Executor id: " + task.getExecutor().getId());
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .authorId(task.getAuthor().getId())
                .projectId(task.getProject().getId())
                .status(task.getStatus())
                .details(details)
                .build();
    }

}
