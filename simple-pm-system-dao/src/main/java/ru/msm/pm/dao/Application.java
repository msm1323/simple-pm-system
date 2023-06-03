package ru.msm.pm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.msm.pm.dao.config.AppConfig;
import ru.msm.pm.dao.impls.jdbc_dao.EmployeeFilter;
import ru.msm.pm.enums.EmployeeStatus;
import ru.msm.pm.model.Employee;
import ru.msm.pm.model.Task;

import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
@Import(AppConfig.class)
public class Application implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class);

    }

    @Override
    public void run(String... args) throws Exception {
//        EmployeeFilter filter = new EmployeeFilter();
//        filter.setProjectId(1L);
//        filter.setStatus(EmployeeStatus.ACTIVE);
//        System.out.println(employeeRepository.findAll(EmployeeSpecification.getSpec(filter)).get(1));

//        System.out.println(Arrays.toString(employeeRepository.findAll(EmployeeSpecification
//                .getSpecByValue("Иванович 4.0")).toArray()));
//
//        Employee employee = new Employee("name__45", "surname_45");
//        System.out.println(employeeRepository.createEmployee(employee));

//        Employee e = new Employee("NAME upd", "SURNAME upd");
//        e.setId(22L);
//        System.out.println(employeeRepository.updateEmployee(e));
//        System.out.println(employeeRepository.deleteEmployee(e));
//        System.out.println(employeeRepository.getEmployeeById(21L));

        System.out.println(taskRepository.getTaskById(1L));

    }
}
