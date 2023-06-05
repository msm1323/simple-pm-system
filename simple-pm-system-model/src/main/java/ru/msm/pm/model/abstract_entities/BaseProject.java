package ru.msm.pm.model.abstract_entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@MappedSuperclass
public abstract class BaseProject implements Serializable {

    //обязательные:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private Long id;  //сур-й PK

    @Column(name = "code_name", nullable = false, unique = true)
    private String codeName;    //Является обязательным и уникальным среди всех проектов.

    @Column(name = "name", nullable = false)
    private String name;

    //необязательные:
    @Column(name = "description")
    private String description;

    protected BaseProject(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

}
