package ru.msm.pm.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.msm.pm.common.enums.ProjectStatus;
import ru.msm.pm.dao.jpa.repos.ProjectRepository;
import ru.msm.pm.dao.jpa.specs.ProjectSpecification;
import ru.msm.pm.dto.project.*;
import ru.msm.pm.model.Project;
import ru.msm.pm.services.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public ProjectDto getProjectById(GetProjectByIdDto dto) {
        Project project = getIfExistOrElseThrow(dto.getId());
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto getProjectByCodeName(GetProjectByCodeNameDto dto) {
        Project project = projectRepository.getProjectByCodeName(dto.getCodeName()).orElseThrow(
                () -> new NoSuchElementException("The project with code name=" + dto.getCodeName() + " was not found."));
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getProjectsByIds(GetProjectsByIdsDto dto) {
        List<Long> ids = dto.getIds();
        List<Project> projects = new ArrayList<>(ids.size());
        for (Long id : ids) {
            projectRepository.getProjectById(id).ifPresent(projects::add);
        }
        return projects.stream().map(p -> mapper.map(p, ProjectDto.class)).toList();
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream().map(p -> mapper.map(p, ProjectDto.class)).toList();
    }

    @Override
    public ProjectDto createProject(CreateProjectDto dto) {
        if (projectRepository.getProjectByCodeName(dto.getCodeName()).isPresent()) {
            throw new IllegalArgumentException("An project with code name=" + dto.getCodeName() + " already exists.");
        }
        Project project = mapper.map(dto, Project.class);
        Long id = projectRepository.createProject(project);
        project.setId(id);
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(UpdateProjectDto dto) {
        getIfExistOrElseThrow(dto.getId());
        if (!projectRepository.getProjectByCodeName(dto.getCodeName()).orElseThrow().getId().equals(dto.getId())) {
            throw new IllegalArgumentException("An project with code name=" + dto.getCodeName() + " already exists.");
        }
        Project project = mapper.map(dto, Project.class);
        System.out.println("project = " + project);
        project = projectRepository.updateProject(project).orElseThrow();
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProjectStatus(UpdateProjectStatusDto dto) {
        Project project = getIfExistOrElseThrow(dto.getId());
        int i = project.getStatus().ordinal() + 1;
        int newStatusIndex = i >= ProjectStatus.values().length ? ProjectStatus.values().length - 1 : i;
        project = projectRepository.updateProjectStatus(project.getId(), ProjectStatus.values()[newStatusIndex].name())
                .orElseThrow();
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto deleteProjectById(DeleteProjectByIdDto dto) {
        Project project = getIfExistOrElseThrow(dto.getId());
        projectRepository.deleteProjectById(dto.getId());
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto deleteProjectByCodeName(DeleteProjectByCodeNameDto dto) {
        Project project = projectRepository.getProjectByCodeName(dto.getCodeName()).orElseThrow(
                () -> new NoSuchElementException("The project with code name=" + dto.getCodeName() + " was not found."));
        projectRepository.deleteProjectByCodeName(dto.getCodeName());
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getProjectsByFilter(GetProjectsByFilterDto dto){
        List<Project> projects = projectRepository
                .findAll(ProjectSpecification.statusListAndTextEqualValue(dto.getStatuses(), dto.getValue()));
        return projects.stream().map(p -> mapper.map(p, ProjectDto.class)).toList();
    }

    private Project getIfExistOrElseThrow(long id) {
        return projectRepository.getProjectById(id)
                .orElseThrow(() -> new NoSuchElementException("The project with id=" + id + " was not found."));
    }

}
