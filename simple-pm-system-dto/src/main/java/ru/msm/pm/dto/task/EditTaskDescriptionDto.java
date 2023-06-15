package ru.msm.pm.dto.task;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditTaskDescriptionDto {

    //обязательные для выполнения запроса поле
    private Long id;
    private String description;

}
