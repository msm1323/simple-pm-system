package ru.msm.pm.dto.project;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateProjectDto {

    private Long id;            //обязательное для выполнения запроса поле
    private String codeName;    //Является обязательным и уникальным среди всех проектов.
    private String name;
    protected String description;

}
