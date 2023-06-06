package ru.msm.pm.dto.project;

import lombok.Data;
import ru.msm.pm.common.enums.ProjectStatus;

@Data
public class FindProjectsDto {

    private String value;   //проверяется по атрибутам Наименование проекта, Код проекта
    private ProjectStatus[] statuses;

}
