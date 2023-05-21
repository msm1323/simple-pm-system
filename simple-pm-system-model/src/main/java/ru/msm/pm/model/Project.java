package ru.msm.pm.model;

import lombok.*;

import ru.msm.pm.enums.ProjectRole;
import ru.msm.pm.enums.ProjectStatus;
import ru.msm.pm.model.abstractEntities.BaseProject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Project extends BaseProject implements Serializable {

    //обязательные:
    private ProjectStatus status;

    //необязательные:
    private List<Task> tasks;
    private Map<Employee, ProjectRole> team;

    @Override
    public String toString() {
        return "Project{" + "codeName='" + codeName + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public Project(String name, String codeName) {
        super(name, codeName);
        status = ProjectStatus.DRAFT;
    }

}
