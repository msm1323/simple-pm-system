package ru.msm.pm.model;

import lombok.*;
import ru.msm.pm.enums.ProjectRole;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "project_participant")
public class Participant {

    //обязательные:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "employee")
    private Employee employee;  //todo

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ProjectRole role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project")
    private Project project;

    public Participant(Employee employee, Project project, ProjectRole role) {
        this.employee = employee;
        this.project = project;
        this.role = role;
    }
}
