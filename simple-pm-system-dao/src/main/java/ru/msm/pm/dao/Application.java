package ru.msm.pm.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.msm.pm.model.Employee;
import ru.msm.pm.dao.config.DaoConfig;

import java.sql.SQLException;

@RequiredArgsConstructor
@SpringBootApplication
@Import(DaoConfig.class)
public class Application implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    private final TaskRepository taskRepository;

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

        Employee e = new Employee("NAME upd", "SURNAME upd");
        e.setId(3L);
        e.setPassword("fdgsfdg");
        System.out.println(employeeRepository.updateEmployee(e));
//        System.out.println(employeeRepository.deleteEmployee(e));
//        System.out.println(employeeRepository.getEmployeeById(1L));

//        System.out.println(taskRepository.getTaskById(1L));

    }
}
