package ru.msm.pm.model.abstract_entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseTask implements Serializable {

    //обязательные:
    protected Long id;  //сур-й PK
    protected String name;
    protected Date dateCreated  = new Date();
    protected Date dateUpdated = dateCreated;//дата последнего редактирования задачи (но не изменение статуса задачи)

    //необязательные:
    protected String description;

    protected BaseTask(String name) {
        this.name = name;
    }


}
