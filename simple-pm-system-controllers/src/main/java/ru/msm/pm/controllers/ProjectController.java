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
import ru.msm.pm.dto.project.*;
import ru.msm.pm.services.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "ProjectController", description = "Операции над проектами")
public class ProjectController {

    private final ProjectService projectService;

    public static final Logger LOGGER = LogManager.getLogger(ProjectController.class);

    @Operation(summary = "Получение проектной информации по его идентификатору")
    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable Long id) {
        GetProjectByIdDto dto = new GetProjectByIdDto(id);
        try {
            return projectService.getProjectById(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение проектной информации по коду проекта")
    @GetMapping("/codename")
    public ProjectDto getProjectByCodeName(@RequestParam String codeName) {
        GetProjectByCodeNameDto dto = new GetProjectByCodeNameDto(codeName);
        try {
            return projectService.getProjectByCodeName(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение информации по проектам по списку их идентификаторов")
    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDto> getProjectsByIds(@RequestBody GetProjectsByIdsDto dto) {
        try {
            return projectService.getProjectsByIds(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение информации обо всех проектах")
    @GetMapping("/")
    public List<ProjectDto> getAllProjects(){
        try {
            return projectService.getAllProjects();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Создание проекта")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@RequestBody CreateProjectDto dto){
        try {
            return projectService.createProject(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Обновление информации по проекту")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto updateProject(@RequestBody UpdateProjectDto dto){
        try {
            return projectService.updateProject(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Обновление статуса проекта")
    @PutMapping(value = "/update-status")
    public ProjectDto updateProjectStatus(@RequestParam Long id){
        UpdateProjectStatusDto dto = new UpdateProjectStatusDto(id);
        try {
            return projectService.updateProjectStatus(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Удаление проекта по его идентификатору")
    @DeleteMapping("/delete")
    public ProjectDto deleteProjectById(@RequestParam Long id){
        DeleteProjectByIdDto dto = new DeleteProjectByIdDto(id);
        try {
            return projectService.deleteProjectById(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Удаление проекта по коду проекта")
    @DeleteMapping("/delete")
    public ProjectDto deleteProjectByCodeName(@RequestParam String codeName) {
        DeleteProjectByCodeNameDto dto = new DeleteProjectByCodeNameDto(codeName);
        try {
            return projectService.deleteProjectByCodeName(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение информации по проектам по заданным фильтрам")
    @GetMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDto> getProjectsByFilter(@RequestBody GetProjectsByFilterDto dto){
        try {
            return projectService.getProjectsByFilter(dto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
