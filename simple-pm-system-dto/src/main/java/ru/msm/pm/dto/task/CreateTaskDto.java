package ru.msm.pm.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateTaskDto {

    //    private MemberDto author; // todo можно вытащить авторизовавшегося уже в сервисе?
    private String name;
    private String projectCodeName;
    private Integer laborCosts;
    private Date deadline;


}
