package ru.msm.pm.model.abstract_entities;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseProject implements Serializable {

    //обязательные:
    protected Long id;  //сур-й PK
    protected String codeName;    //Является обязательным и уникальным среди всех проектов.
    protected String name;

    //необязательные:
    protected String description;

    protected BaseProject(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

}
