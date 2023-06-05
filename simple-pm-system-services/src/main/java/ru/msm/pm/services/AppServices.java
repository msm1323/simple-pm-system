package ru.msm.pm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ru.msm.pm.services.config.ServicesConfig;

import java.sql.SQLException;

@SpringBootApplication
@Import(ServicesConfig.class)
public class AppServices implements CommandLineRunner {

//    @Autowired
//    EmployeeService employeeService;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(AppServices.class);

    }

    @Override
    public void run(String... args) throws Exception {
//        CreateEmployeeDto dto = CreateEmployeeDto.builder()
//                .name("serv_name").surname("serv_surname").build();
//        System.out.println(employeeService.create(dto));

//        GetEmployeeByIdDto dto = new GetEmployeeByIdDto(1L);
//        System.out.println(employeeService.getEmployeeById(dto));

//        GetEmployeeByAccountDto dto = GetEmployeeByAccountDto.builder().account("for4four").build();
//        System.out.println(employeeService.getEmployeeByAccount(dto));

//        FindEmployeesDto dto = FindEmployeesDto.builder().value("surname_a").build();
//        System.out.println(employeeService.findByValue(dto));
    }
}
