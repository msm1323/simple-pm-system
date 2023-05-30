package ru.msm.pm.model;

import lombok.*;

import lombok.experimental.SuperBuilder;
import ru.msm.pm.enums.ProjectStatus;
import ru.msm.pm.model.abstract_entities.BaseProject;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class Project extends BaseProject implements Serializable {

    //обязательные:
    private ProjectStatus status = ProjectStatus.DRAFT;

    //необязательные:
    private List<Task> tasks;
    private List<Participant> team;

    @Override
    public String toString() {
        return "Project{" + "codeName='" + super.getCodeName() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", status=" + status +
                ", description='" + super.getDescription() + '\'' +
                '}';
    }

    public Project(String name, String codeName) {
        super(name, codeName);
    }

}
