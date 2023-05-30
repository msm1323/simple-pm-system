package ru.msm.pm.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.msm.pm.enums.EmployeeStatus;
import ru.msm.pm.model.abstract_entities.Member;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class Employee extends Member implements Serializable {

    //обязательные:
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    //необязательные:
    private String patronymic;
    private String position;
    private String account;
    private String email;

    public Employee(String name, String surname) {
        super(name, surname);
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
