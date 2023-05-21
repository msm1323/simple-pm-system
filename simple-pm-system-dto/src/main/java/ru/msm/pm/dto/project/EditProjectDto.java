package ru.msm.pm.dto.project;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class EditProjectDto {

    private String codeName;
    private Map<String, Object> fields;

}
