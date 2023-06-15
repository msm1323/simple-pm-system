package ru.msm.pm.services;

import ru.msm.pm.dto.project.*;

import java.util.List;

public interface ProjectService {

    ProjectDto getProjectById(GetProjectByIdDto dto);

    ProjectDto getProjectByCodeName(GetProjectByCodeNameDto dto);

    List<ProjectDto> getProjectsByIds(GetProjectsByIdsDto dto);

    List<ProjectDto> getProjectsByFilter(GetProjectsByFilterDto dto);

    List<ProjectDto> getAllProjects();

    ProjectDto createProject(CreateProjectDto dto);

    ProjectDto updateProject(UpdateProjectDto dto);

    ProjectDto updateProjectStatus(UpdateProjectStatusDto dto);

    ProjectDto deleteProjectById(DeleteProjectByIdDto dto);

    ProjectDto deleteProjectByCodeName(DeleteProjectByCodeNameDto dto);

}
