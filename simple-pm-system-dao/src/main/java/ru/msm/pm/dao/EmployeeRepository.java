package ru.msm.pm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.msm.pm.model.Employee;

import javax.transaction.Transactional;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> getEmployeeById(Long id);

    Optional<Employee> getEmployeeByAccount(String account);

//    @Query(value = "insert into employee (name, surname, status, patronymic, account, position, email)" +
//            " values (:#{#e.name}, :#{#e.surname}, :#{#e.status.name()}, :#{#e.patronymic}, :#{#e.account}," +
//            " :#{#e.position}, :#{#e.email}) RETURNING id", nativeQuery = true)
//    Long createEmployee(@Param("e") Employee e);

    @Query(value = "insert into employee (name, surname, status)" +
            " values (:#{#e.name}, :#{#e.surname}, :#{#e.status.name()}) RETURNING id", nativeQuery = true)
    Long createEmployee(@Param("e") Employee e);

    @Query(value = "UPDATE employee set name=:#{#e.name}, surname=:#{#e.surname}, patronymic=:#{#e.patronymic}," +
            " account=:#{#e.account}, password=:#{#e.password}, position=:#{#e.position}, email=:#{#e.email}" +
            " WHERE id=:#{#e.id} returning *"
            , nativeQuery = true)
    Employee updateEmployee(@Param("e") Employee e);

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE employee set status='DELETED' WHERE id=:#{#e.id}", nativeQuery = true)  //todo можно ли и нужно ли как-то вызвать здесь enum?
//    int deleteEmployee(@Param("e") Employee e);

    @Query(value = "UPDATE employee set status='DELETED' WHERE id=:id returning *", nativeQuery = true)  //
    Employee deleteEmployeeById(@Param("id") Long id);

}
