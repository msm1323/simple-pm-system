package ru.msm.pm.dto.task;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetTaskByIdDto {

    Long id;

}
