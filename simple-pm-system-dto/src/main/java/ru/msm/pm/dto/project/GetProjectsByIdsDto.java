package ru.msm.pm.dto.project;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetProjectsByIdsDto {

    List<Long> ids;        //обязательное для выполнения запроса поле

}
