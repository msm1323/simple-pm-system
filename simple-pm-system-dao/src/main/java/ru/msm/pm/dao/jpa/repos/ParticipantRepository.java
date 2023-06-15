package ru.msm.pm.dao.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.msm.pm.model.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long>, JpaSpecificationExecutor<Participant> {

    Optional<Participant> getParticipantById(Long id);

    @Query(value = "insert into project_participant (employee, role, project)" +
            " values (:#{#p.employee.id}, :#{#p.role.name()}, :#{#p.project.id}) RETURNING id", nativeQuery = true)
    Long createParticipant(@Param("p") Participant p);

    @Query(value = "delete from project_participant WHERE id=:id returning *", nativeQuery = true)
    Optional<Participant> deleteParticipantById(@Param("id") Long id);

    @Query(value = "select p from Participant p WHERE p.project.id=:id")
    List<Participant> getParticipantsByProjectId(@Param("id") Long id);

    List<Participant> getParticipantsByEmployee_Id(Long id);
}
