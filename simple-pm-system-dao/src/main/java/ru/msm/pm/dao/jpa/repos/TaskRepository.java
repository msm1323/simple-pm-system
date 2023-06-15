package ru.msm.pm.dao.jpa.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.msm.pm.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    Optional<Task> getTaskById(Long id);

    @Query(value = "select t from Task t where t.project.id=:id")
    List<Task> getTasksByProjectId(@Param("id") Long id, Sort sort);

    @Query(value = "insert into task (name, date_created, date_updated, author, status, project," +
            " labor_costs, deadline, description, executor) values" +
            " (:#{#t.name}, :#{#t.dateCreated}, :#{#t.dateUpdated}, :#{#t.author.id}, :#{#t.status.name()}, :#{#t.project.id}," +
            " :#{#t.laborCosts}, :#{#t.deadline}, :#{#t.description}, :#{#t.executor.id}) RETURNING id", nativeQuery = true)
    Long createFullTask(@Param("t") Task t);

    @Query(value = "insert into task (name, date_created, date_updated, author, status, project, labor_costs, deadline)" +
            " values (:#{#t.name}, :#{#t.dateCreated}, :#{#t.dateUpdated}, :#{#t.author.id}, :#{#t.status.name()}," +
            " :#{#t.project.id}, :#{#t.laborCosts}, :#{#t.deadline}) RETURNING id", nativeQuery = true)
    Long createTask(@Param("t") Task t);

    @Query(value = "UPDATE task set name=:#{#t.name}, date_updated=:#{#t.dateUpdated}, author=:#{#t.author.id}," +
            " labor_costs=:#{#t.laborCosts}, deadline=:#{#t.deadline}, description=:#{#t.description}," +
            " executor=:#{#t.executor.id} WHERE id=:#{#t.id} returning *", nativeQuery = true)
    Optional<Task> updateFullTask(@Param("t") Task t);

    @Query(value = "UPDATE task set name=:#{#t.name}, date_updated=:#{#t.dateUpdated}, author=:#{#t.author.id}," +
            " labor_costs=:#{#t.laborCosts}, deadline=:#{#t.deadline} WHERE id=:#{#t.id} returning *", nativeQuery = true)
    Optional<Task> updateTask(@Param("t") Task t);

    @Query(value = "UPDATE task set description=:#{#t.description} WHERE id=:#{#t.id} returning *", nativeQuery = true)
    Optional<Task> updateTaskDescription(@Param("t") Task t);

    @Query(value = "update task set status=:s WHERE id=:id returning *", nativeQuery = true)
    Optional<Task> updateTaskStatus(@Param("id") Long id, @Param("s") String newStatus);

    @Query(value = "update Task set executor=null WHERE executor=:id")
    void deleteTaskExecutor(@Param("id") Long executorId);

}
