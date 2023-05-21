package ru.msm.pm.dto.projectTeam;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DeleteParticipantDto {

    private BigDecimal id;
    private String projectCodeName;

}
