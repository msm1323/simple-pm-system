package ru.msm.pm.model;

import lombok.*;
import ru.msm.pm.enums.TaskStatus;
import ru.msm.pm.model.abstractEntities.BaseTask;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task extends BaseTask implements Serializable {

    //обязательные:
    private Employee author;
    private TaskStatus status;
    private Project project;
    private Integer laborCosts;
    private Date deadline;

    //необязательные:
    private Employee executor;

    public Task(String name, Project project, Employee author, Integer laborCosts, Date deadline) {
        super(name);
        this.project = project;
        this.author = author;
        this.laborCosts = laborCosts;
        this.deadline = deadline;
        status = TaskStatus.NEW;
    }

}
