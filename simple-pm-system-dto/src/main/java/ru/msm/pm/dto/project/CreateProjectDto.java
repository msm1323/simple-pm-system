package ru.msm.pm.dto.project;

import lombok.*;

//todo openAPI описания везде
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateProjectDto {
    //обязательные для выполнения запроса поля
    private String name;
    private String codeName;

    //необязательные
    private String description;

}
