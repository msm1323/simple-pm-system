package ru.msm.pm.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.msm.pm.enums.TaskStatus;
import ru.msm.pm.model.abstract_entities.BaseTask;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class Task extends BaseTask implements Serializable {

    //обязательные:
    private Employee author;
    private TaskStatus status = TaskStatus.NEW;
    private Project project;
    private Integer laborCosts;
    private Date deadline;

    //необязательные:
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
                ", description='" + super.getDescription() + '\'' +
                ", executor.id=" + executor.getId() +
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
