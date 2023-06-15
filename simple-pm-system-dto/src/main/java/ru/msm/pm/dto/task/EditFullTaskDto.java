package ru.msm.pm.dto.task;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditFullTaskDto {

    private Long id;        //обязательное для выполнения запроса поле
    private String name;
    private Integer laborCosts;
    private Date deadline;
    private String description;
    private Long executorId; //обяз
}
