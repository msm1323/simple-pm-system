package ru.msm.pm.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.msm.pm.common.enums.EmployeeStatus;
import ru.msm.pm.model.abstract_entities.Member;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Table(name = "employee")
public class Employee extends Member implements Serializable {

    //обязательные:
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    @Column(name = "password") // todo
    private String password;

    //необязательные:
    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "position")
    private String position;

    @Column(name = "account", unique = true)
    private String account;

    @Column(name = "email")
    private String email;

    public Employee(String name, String surname) {
        super(name, surname);
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                "status=" + status +
                (patronymic == null ? "" : (", patronymic='" + patronymic + '\'')) +
                (position == null ? "" : (", position='" + position + '\'')) +
                (account == null ? "" : (", account='" + account + '\'')) +
                (email == null ? "" : (", email='" + email + '\'')) +
                "} ";
    }
}
