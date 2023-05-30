package ru.msm.pm.model;

import lombok.*;
import ru.msm.pm.enums.ProjectRole;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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
