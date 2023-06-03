package ru.msm.pm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ru.msm.pm.dto.employee.*;
import ru.msm.pm.services.config.AppConfig;

import java.sql.SQLException;

@SpringBootApplication
@Import(AppConfig.class)
public class Application implements CommandLineRunner {

    @Autowired
    EmployeeService employeeService;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class);

    }

    @Override
    public void run(String... args) throws Exception {
//        CreateEmployeeDto dto = CreateEmployeeDto.builder()
//                .name("serv_name").surname("serv_surname").build();
//        System.out.println(employeeService.create(dto));

//        GetEmployeeByIdDto dto = GetEmployeeByIdDto.builder()
//                        .id(43L).build();
//        System.out.println(employeeService.getEmployeeById(dto));

//        GetEmployeeByAccountDto dto = GetEmployeeByAccountDto.builder().account("for4four").build();
//        System.out.println(employeeService.getEmployeeByAccount(dto));

//        FindEmployeesDto dto = FindEmployeesDto.builder().value("surname_a").build();
//        System.out.println(employeeService.findByValue(dto));
    }
}
