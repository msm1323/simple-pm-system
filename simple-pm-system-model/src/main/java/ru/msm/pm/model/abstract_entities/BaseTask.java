package ru.msm.pm.model.abstract_entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
@Inheritance
public abstract class BaseTask implements Serializable {

    //обязательные:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private Long id;  //сур-й PK

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated  = new Date();

    @Column(name = "date_updated", nullable = false)
    private Date dateUpdated = dateCreated;//дата последнего редактирования задачи (но не изменение статуса задачи)

    //необязательные:
    @Column(name = "description")
    private String description;

    protected BaseTask(String name) {
        this.name = name;
    }


}
