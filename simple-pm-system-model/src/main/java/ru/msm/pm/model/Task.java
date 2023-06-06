package ru.msm.pm.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.msm.pm.common.enums.TaskStatus;
import ru.msm.pm.model.abstract_entities.BaseTask;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Table(name = "task")
public class Task extends BaseTask implements Serializable {

    //обязательные:
    @ManyToOne(optional = false)
    @JoinColumn(name = "author")
    private Employee author;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NEW;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project")
    private Project project;

    @Column(name = "labor_costs", nullable = false)
    private Integer laborCosts;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    //необязательные:
    @ManyToOne
    @JoinColumn(name = "executor")
    private Employee executor;

    @Override
    public String toString() {
        return "Task{" + "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", status=" + status +
                ", author.id=" + author.getId() +
                ", project.codeName='" + project.getCodeName() + '\'' +
                ", laborCosts=" + laborCosts +
                ", deadline=" + deadline +
                ", dateCreated=" + super.getDateCreated() +
                ", dateUpdated=" + super.getDateUpdated() +
                (super.getDescription() == null ? "" : (", description.id='" + super.getDescription() + '\'')) +
                (executor == null ? "" : (", executor.id=" + executor.getId())) +
                '}';
    }

    public Task(String name, Project project, Employee author, Integer laborCosts, Date deadline) {
        super(name);
        this.project = project;
        this.author = author;
        this.laborCosts = laborCosts;
        this.deadline = deadline;
    }

}
