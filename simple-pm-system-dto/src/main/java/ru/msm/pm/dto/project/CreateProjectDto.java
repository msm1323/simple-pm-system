package ru.msm.pm.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectDto {
    //обязательные для выполнения запроса поляЖ
    private String name;
    private String codeName;

}
