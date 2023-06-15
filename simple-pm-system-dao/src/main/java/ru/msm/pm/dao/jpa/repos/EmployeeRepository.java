package ru.msm.pm.dao.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.msm.pm.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> getEmployeeById(Long id);

    Optional<Employee> getEmployeeByAccount(String account);

    @Query(value = "insert into employee (name, surname, status, patronymic, account, password, position, email)" +
            " values (:#{#e.name}, :#{#e.surname}, :#{#e.status.name()}, :#{#e.patronymic}, :#{#e.account}," +
            " :#{#e.password}, :#{#e.position}, :#{#e.email}) RETURNING id", nativeQuery = true)
    Long createFullEmployee(@Param("e") Employee e);

    @Query(value = "insert into employee (name, surname, status)" +
            " values (:#{#e.name}, :#{#e.surname}, :#{#e.status.name()}) RETURNING id", nativeQuery = true)
    Long createEmployee(@Param("e") Employee e);

    @Query(value = "UPDATE employee set account=:#{#e.account}, password=:#{#e.password} WHERE id=:#{#e.id} returning *"
            , nativeQuery = true)
    Optional<Employee> setCredentials(@Param("e") Employee e);

    @Query(value = "UPDATE employee set name=:#{#e.name}, surname=:#{#e.surname}, patronymic=:#{#e.patronymic}," +
            " account=:#{#e.account}, password=:#{#e.password}, position=:#{#e.position}, email=:#{#e.email}" +
            " WHERE id=:#{#e.id} returning *"
            , nativeQuery = true)
    Optional<Employee> updateEmployee(@Param("e") Employee e);

    //у удаленного сотрудника нет учетных данных
    @Query(value = "UPDATE employee set status='DELETED', account=null, password=null" +
            " WHERE id=:id returning *", nativeQuery = true)  //
    Optional<Employee> deleteEmployeeById(@Param("id") Long id);

}
