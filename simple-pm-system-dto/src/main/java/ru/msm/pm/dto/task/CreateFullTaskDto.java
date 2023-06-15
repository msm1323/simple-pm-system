package ru.msm.pm.dto.task;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateFullTaskDto {
    //обязательные для выполнения запроса поля:
    private String name;
    private Long projectId;
    private Integer laborCosts;
    private Date deadline;
    private String description;
    private Long executorId;    //обяз

}
