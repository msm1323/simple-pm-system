package ru.msm.pm.dto.task;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateTaskDto {
    //обязательные для выполнения запроса поля:
    private String name;
    private Long projectId;
    private Integer laborCosts;
    private Date deadline;


}
