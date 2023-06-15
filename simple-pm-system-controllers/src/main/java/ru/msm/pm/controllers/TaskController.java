package ru.msm.pm.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.msm.pm.dto.project.GetProjectByIdDto;
import ru.msm.pm.dto.task.*;
import ru.msm.pm.services.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "TaskController", description = "Операции над задачами")
public class TaskController {

    private final TaskService taskService;

    public static final Logger LOGGER = LogManager.getLogger(TaskController.class);

    @Operation(summary = "Получение информации о задаче по её идентификатору")
    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        GetTaskByIdDto dto = new GetTaskByIdDto(id);
        try {
            return taskService.getTaskById(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение информации о задачах проекта по его идентификатору")
    @GetMapping("/project/{projectId}")
    public List<TaskDto> getTasksByProject(@PathVariable Long projectId) {
        try {
            GetTasksByProjectIdDto dto = new GetTasksByProjectIdDto(projectId);
            return taskService.getTasksByProjectId(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение информации о задачах по заданным фильтрам")
    @GetMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTasksByFilter(@RequestBody FindTasksDto dto) {
        try {
            return taskService.getTasksByFilter(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Создание задачи (с полным набором полей)")
    @PostMapping(value = "/create-full", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createFullTask(@RequestBody CreateFullTaskDto dto) {
        try {
            return taskService.createFullTask(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Создание задачи (упрощенное)")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody CreateTaskDto dto) {
        try {
            return taskService.createTask(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Обновление информации о задаче (с полным набором полей)")
    @PutMapping(value = "/update-full", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateFullTask(@RequestBody EditFullTaskDto dto) {
        try {
            return taskService.updateFullTask(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Обновление информации о задаче (упрощенное)")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody EditTaskDto dto) {
        try {
            return taskService.updateTask(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Обновление описания задачи")
    @PutMapping(value = "/update-description", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTaskDescription(@RequestBody EditTaskDescriptionDto dto) {
        try {
            return taskService.updateTaskDescription(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Обновление статуса задачи")
    @PutMapping(value = "/update-status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTaskStatus(@RequestBody UpdateTaskStatusDto dto) {
        try {
            return taskService.updateTaskStatus(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
