package ru.msm.pm.model;

import lombok.*;
import ru.msm.pm.enums.EmployeeStatus;
import ru.msm.pm.model.abstractEntities.Member;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee extends Member implements Serializable {

    //обязательные:
    private EmployeeStatus status;

    //необязательные:
    private String patronymic;
    private String position;
    private String account;
    private String email;

    public Employee(String name, String surname) {
        super(name, surname);
        status = EmployeeStatus.ACTIVE;
    }

}
