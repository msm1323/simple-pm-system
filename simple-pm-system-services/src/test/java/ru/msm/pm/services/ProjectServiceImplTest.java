package ru.msm.pm.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import ru.msm.pm.common.enums.ProjectStatus;
import ru.msm.pm.dao.jpa.repos.ProjectRepository;
import ru.msm.pm.dto.project.*;
import ru.msm.pm.model.Project;
import ru.msm.pm.services.config.ServicesConfig;
import ru.msm.pm.services.impls.ProjectServiceImpl;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ServicesConfig.class)
@PropertySource(value = "application-test.properties")
@EnableAutoConfiguration
public class ProjectServiceImplTest extends BaseTest {

    @Spy
    ProjectRepository repository;

    @InjectMocks
    ProjectServiceImpl service;

    @Test
    public void testGetProjectById() {
        GetProjectByIdDto requestDto = new GetProjectByIdDto(new Random().nextLong());
        Project dbPrj = new Project(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        dbPrj.setId(requestDto.getId());
        when(repository.getProjectById(requestDto.getId())).thenReturn(Optional.of(dbPrj));

        ProjectDto resultDto = service.getProjectById(requestDto);

        assertThat(resultDto.getId()).isEqualTo(dbPrj.getId());
        assertThat(resultDto.getCodeName()).isEqualTo(dbPrj.getCodeName());
        assertThat(resultDto.getName()).isEqualTo(dbPrj.getName());
        assertThat(resultDto.getStatus()).isEqualTo(dbPrj.getStatus());
        assertThat(resultDto.getDescription()).isEqualTo(dbPrj.getDescription());
    }

    @Test
    public void testGetProjectByCodeName() {
        GetProjectByCodeNameDto requestDto = new GetProjectByCodeNameDto(UUID.randomUUID().toString());
        Project dbPrj = new Project(UUID.randomUUID().toString(), requestDto.getCodeName());
        dbPrj.setId(new Random().nextLong());
        when(repository.getProjectByCodeName(requestDto.getCodeName())).thenReturn(Optional.of(dbPrj));

        ProjectDto resultDto = service.getProjectByCodeName(requestDto);

        assertThat(resultDto.getId()).isEqualTo(dbPrj.getId());
        assertThat(resultDto.getCodeName()).isEqualTo(dbPrj.getCodeName());
        assertThat(resultDto.getName()).isEqualTo(dbPrj.getName());
        assertThat(resultDto.getStatus()).isEqualTo(dbPrj.getStatus());
        assertThat(resultDto.getDescription()).isEqualTo(dbPrj.getDescription());
    }

    @Test
    public void testCreateProject() {
        CreateProjectDto requestDto = CreateProjectDto.builder()
                .name(UUID.randomUUID().toString()).codeName(UUID.randomUUID().toString()).build();
        Long dbId = new Random().nextLong();
        Project project = new Project(requestDto.getName(), requestDto.getCodeName());

        when(repository.createProject(eq(project))).thenReturn(dbId);

        ProjectDto resultDto = service.createProject(requestDto);

        assertThat(resultDto.getId()).isEqualTo(dbId);
        assertThat(resultDto.getCodeName()).isEqualTo(requestDto.getCodeName());
        assertThat(resultDto.getName()).isEqualTo(requestDto.getName());
        assertThat(resultDto.getStatus()).isEqualTo(ProjectStatus.DRAFT);
        assertThat(resultDto.getDescription()).isEqualTo(requestDto.getDescription());

//        System.out.println(resultDto);
    }

    @Test
    public void testUpdateProject(){
//        EditProjectDto requestDto = EditProjectDto.builder()
//                .id(new Random().nextLong())
//                .name(UUID.randomUUID().toString())
//                .codeName(UUID.randomUUID().toString())
//                .description(UUID.randomUUID().toString()).build();
//        Project servPrj = Project.builder()
//                .name(requestDto.getName())
//                .codeName(requestDto.getCodeName())     //todo без статуса
//                .id(requestDto.getId())
//                .description(requestDto.getDescription()).build();
//        Project dbPj = Project.builder()
//                .name(UUID.randomUUID().toString())
//                .codeName(requestDto.getCodeName())     //todo без статуса
//                .id(requestDto.getId()).build();
//
//        when(repository.getProjectById(eq(servPrj.getId()))).thenReturn(Optional.of(dbPj));
//        when(repository.getProjectByCodeName(eq(servPrj.getCodeName()))).thenReturn(Optional.of(dbPj));
//        when(repository.updateProject(eq(servPrj))).thenReturn(Optional.of(servPrj));
//
//        ProjectDto resultDto = service.updateProject(requestDto);
//
//        assertThat(resultDto.getId()).isEqualTo(requestDto.getId());
//        assertThat(resultDto.getCodeName()).isEqualTo(requestDto.getCodeName());
//        assertThat(resultDto.getName()).isEqualTo(requestDto.getName());
////        assertThat(resultDto.getStatus()).isEqualTo(ProjectStatus.DRAFT);
//        assertThat(resultDto.getDescription()).isEqualTo(requestDto.getDescription());

    }

}
