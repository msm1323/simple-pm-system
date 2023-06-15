package ru.msm.pm.dto.project;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateProjectStatusDto {

    private Long id;        //обязательное для выполнения запроса поле

}
