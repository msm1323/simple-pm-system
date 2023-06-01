package ru.msm.pm.dao.impls.jdbc_dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import ru.msm.pm.dao.config.AppConfig;
import ru.msm.pm.enums.EmployeeStatus;
import ru.msm.pm.model.Employee;

import java.sql.SQLException;

//@EnableAutoConfiguration
@SpringBootApplication
@Import(AppConfig.class)
public class Application implements CommandLineRunner {

    @Autowired
    private EmployeeJdbcDaoImpl employeeJdbcDao;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class);
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        EmployeeJdbcDaoImpl employeeJdbcDao = context.getBean(EmployeeJdbcDaoImpl.class);
//        System.out.println(employeeJdbcDao.getById(1L));
//        System.out.println(employeeJdbcDao);

//        EmployeeJdbcDaoImpl employeeJdbcDao2 = context.getBean(EmployeeJdbcDaoImpl.class);
//        System.out.println(employeeJdbcDao2.getById(10L));
//        System.out.println(employeeJdbcDao2);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(employeeJdbcDao.getById(5L));
//        System.out.println(EmployeeStatus.ACTIVE.toString());
//        Employee employee = new Employee("name__000", "surname_40000");
//        System.out.println(employeeJdbcDao.create(employee));
//        System.out.println(employeeJdbcDao);
    }
}
