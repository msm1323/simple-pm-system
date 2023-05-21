package ru.msm.pm.model.abstractEntities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Member implements Serializable {

    //обязательные:
    protected BigDecimal id;  //сур-й PK
    protected String name;
    protected String surname;

    protected Member(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
