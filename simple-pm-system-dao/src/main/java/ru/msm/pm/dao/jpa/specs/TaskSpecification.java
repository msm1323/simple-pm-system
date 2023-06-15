package ru.msm.pm.dao.jpa.specs;

import org.springframework.data.jpa.domain.Specification;
import ru.msm.pm.common.enums.TaskStatus;
import ru.msm.pm.model.Task;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskSpecification {

    /*
    Поиск задач - задачи должны искать по текстовому значению (по полям Наименование задачи)
     и с применением фильтров (по статусам задачи, по исполнителю, по автору задачи,
      по периоду крайнего срока задачи, по периоду создания задачи). Фильтры все не обязательны, как и текстовое поле.

    Результат должен быть отсортирован по дате создания задачи в обратном порядке (сначала свежие задачи).
     */

    public static Specification<Task> findByFilter(String value, TaskStatus[] statuses, Long executorId, Long authorId,
                                                   Date[] deadlinePeriod, Date[] dateCreatedPeriod) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!value.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("name"), value));
            }
            if (statuses != null) {
                List<Predicate> statusesPredicates = new ArrayList<>();
                for (TaskStatus status : statuses) {
                    statusesPredicates.add(criteriaBuilder.equal(root.get("status"), status));
                }
                Predicate statusesP = query.where(criteriaBuilder.or(statusesPredicates.toArray(Predicate[]::new))).getRestriction();
                predicates.add(statusesP);
            }
            if (executorId != null) {
                predicates.add(criteriaBuilder.equal(root.get("executor").get("id"), executorId));   //todo
            }
            if (authorId != null) {
                predicates.add(criteriaBuilder.equal(root.get("author").get("id"), authorId));   //todo
            }
            if (deadlinePeriod != null) {
                predicates.add(criteriaBuilder.between(root.get("deadline"), deadlinePeriod[0], deadlinePeriod[1]));
            }
            if (dateCreatedPeriod != null) {
                predicates.add(criteriaBuilder.between(root.get("dateCreated"), dateCreatedPeriod[0], dateCreatedPeriod[1]));
            }

            if (predicates.isEmpty()) {
                return query.where().getRestriction();
            }
            return query.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new))).getRestriction();

        });
    }

}
