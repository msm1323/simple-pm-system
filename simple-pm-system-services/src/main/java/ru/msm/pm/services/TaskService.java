package ru.msm.pm.services;

import ru.msm.pm.dto.task.*;

import java.util.List;

public interface TaskService {

    TaskDto getTaskById(GetTaskByIdDto dto);

    List<TaskDto> getTasksByProjectId(GetTasksByProjectIdDto dto);

    List<TaskDto> getTasksByFilter(FindTasksDto dto);

    TaskDto createFullTask(CreateFullTaskDto dto);

    TaskDto createTask(CreateTaskDto dto);

    TaskDto updateFullTask(EditFullTaskDto dto);

    TaskDto updateTask(EditTaskDto dto);

    TaskDto updateTaskDescription(EditTaskDescriptionDto dto);

    TaskDto updateTaskStatus(UpdateTaskStatusDto dto);

}
