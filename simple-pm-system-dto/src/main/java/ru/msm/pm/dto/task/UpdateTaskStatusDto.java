package ru.msm.pm.dto.task;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateTaskStatusDto {

    private Long id;        //обязательное для выполнения запроса поле

}
