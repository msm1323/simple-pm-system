package ru.msm.pm.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.msm.pm.enums.ProjectRole;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Participant {

    //обязательные:
    private Long id;
    private Employee employee;
    private ProjectRole role;
    private Project project;

    public Participant(Employee employee, Project project, ProjectRole role) {
        this.employee = employee;
        this.project = project;
        this.role = role;
    }
}
