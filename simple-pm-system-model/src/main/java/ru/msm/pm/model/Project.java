package ru.msm.pm.model;

import lombok.*;

import lombok.experimental.SuperBuilder;
import ru.msm.pm.common.enums.ProjectStatus;
import ru.msm.pm.model.abstract_entities.BaseProject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Table(name = "project")
public class Project extends BaseProject implements Serializable {

    //обязательные:
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status = ProjectStatus.DRAFT;

    //необязательные:
    @OneToMany (mappedBy="project", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    @OneToMany (mappedBy="project", cascade = CascadeType.REMOVE)
    private List<Participant> team;

    @Override
    public String toString() {
        return "Project{id=" + super.getId() +
                ", codeName='" + super.getCodeName() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", status=" + status +
                (super.getDescription() == null ? "" : (", description.='" + super.getDescription() + '\'')) +
                '}';
    }

    public Project(String name, String codeName) {
        super(name, codeName);
    }

}
