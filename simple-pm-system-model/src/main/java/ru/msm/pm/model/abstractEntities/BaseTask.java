package ru.msm.pm.model.abstractEntities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseTask implements Serializable {

    //обязательные:
    protected BigDecimal id;  //сур-й PK
    protected String name;
    protected Date dateCreated;
    protected Date dateUpdated;   //дата последнего редактирования задачи (но не изменение статуса задачи).

    //необязательные:
    protected String description;

    protected BaseTask(String name) {
        this.name = name;
        this.dateCreated = new Date();
        this.dateUpdated = dateCreated;
    }


}
