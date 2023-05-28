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

    public Participant(Employee employee, ProjectRole role) {
        this.employee = employee;
        this.role = role;
    }
}
