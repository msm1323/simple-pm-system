package ru.msm.pm.model.abstract_entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Member implements Serializable {

    //обязательные:
    private Long id;  //сур-й PK
    private String name;
    private String surname;

    protected Member(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
