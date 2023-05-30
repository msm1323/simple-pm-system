package ru.msm.pm.model.abstract_entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class BaseTask implements Serializable {

    //обязательные:
    private Long id;  //сур-й PK
    private String name;
    private Date dateCreated  = new Date();
    private Date dateUpdated = dateCreated;//дата последнего редактирования задачи (но не изменение статуса задачи)

    //необязательные:
    private String description;

    protected BaseTask(String name) {
        this.name = name;
    }


}
