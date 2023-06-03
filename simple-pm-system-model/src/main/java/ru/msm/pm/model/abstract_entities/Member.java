package ru.msm.pm.model.abstract_entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
@Inheritance
public abstract class Member implements Serializable {

    //обязательные:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private Long id;  //сур-й PK

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    protected Member(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
