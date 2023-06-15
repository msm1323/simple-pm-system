package ru.msm.pm.dao.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.msm.pm.model.Project;

import java.util.Optional;

/**
 * Операции над проектом:
 * - Создание проекта. При создании проекта должны быть заполнены все обязательные поля,
 * и выполнена проверка на уникальность кода проекта. Проект создается в статусе Черновик.
 * - Изменение проекта. При изменении проекта должны изменяться поля карточки проекта.
 * - Поиск проектов. Поиск должен осуществляться по текстовому значению (по полям Наименование проекта, Код проекта)
 * и с применением фильтров по Статусу проекта. Т.е. на вход передается некоторое текстовое значение и список статусов.
 * - Перевод в другое состояние проекта. Можно перевести в другой статус проект, согласно диаграмме ниже о возможных изменениях статуса.
 */

public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    Optional<Project> getProjectById(Long id);

    Optional<Project> getProjectByCodeName(String codeName);

    @Query(value = "insert into project (name, code_name, status, description)" +
            " values (:#{#p.name}, :#{#p.codeName}, :#{#p.status.name()}, :#{#p.description}) RETURNING id",
            nativeQuery = true)
    Long createProject(@Param("p") Project p);

    @Query(value = "update project set name=:#{#p.name}, code_name=:#{#p.codeName}, description=:#{#p.description}" +
            " WHERE id=:id returning *", nativeQuery = true)
    Optional<Project> updateProject(@Param("p") Project p);

    @Query(value = "update project set status=:s WHERE id=:id returning *", nativeQuery = true)
    Optional<Project> updateProjectStatus(@Param("id") Long id, @Param("s") String newStatus);

    void deleteProjectById(Long id);

    void deleteProjectByCodeName(String codeName);
}
