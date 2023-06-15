package ru.msm.pm.dto.project;

import lombok.*;
import ru.msm.pm.common.enums.ProjectStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetProjectsByFilterDto {

    private String value;   //проверяется по атрибутам Наименование проекта, Код проекта
    private ProjectStatus[] statuses;

}
