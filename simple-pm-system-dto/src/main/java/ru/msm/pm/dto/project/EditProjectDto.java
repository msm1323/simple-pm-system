package ru.msm.pm.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditProjectDto {

    private Long id;            //обязательное для выполнения запроса поле
    private String codeName;    //Является обязательным и уникальным среди всех проектов.
    private String name;
    protected String description;

}
