package ru.msm.pm.dto.task;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditTaskDto {

    private Long id;        //обязательное для выполнения запроса поле
    private String name;
    private Integer laborCosts;
    private Date deadline;

}
