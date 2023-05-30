package ru.msm.pm.model.abstract_entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class BaseProject implements Serializable {

    //обязательные:
    private Long id;  //сур-й PK
    private String codeName;    //Является обязательным и уникальным среди всех проектов.
    private String name;

    //необязательные:
    private String description;

    protected BaseProject(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

}
