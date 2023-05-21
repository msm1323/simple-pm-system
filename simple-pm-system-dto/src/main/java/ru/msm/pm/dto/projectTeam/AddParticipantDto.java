package ru.msm.pm.dto.projectTeam;

import lombok.Getter;
import lombok.Setter;
import ru.msm.pm.enums.ProjectRole;

import java.math.BigDecimal;

@Getter
@Setter
public class AddParticipantDto {

    private BigDecimal id;
    private ProjectRole role;
    private String projectCodeName;

}
