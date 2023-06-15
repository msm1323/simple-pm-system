package ru.msm.pm.dao.jpa.specs;

import org.springframework.data.jpa.domain.Specification;
import ru.msm.pm.common.enums.ProjectStatus;
import ru.msm.pm.model.Project;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {

    /*
    Поиск должен осуществляться по текстовому значению (по полям Наименование проекта, Код проекта)
    и с применением фильтров по Статусу проекта.
    Т.е. на вход передается некоторое текстовое значение и список статусов.
     */

    public static Specification<Project> statusListAndTextEqualValue(ProjectStatus[] statuses, String value) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(2);
            if (statuses != null) {
                List<Predicate> statusesPredicates = new ArrayList<>();
                for (ProjectStatus status : statuses) {
                    statusesPredicates.add(criteriaBuilder.equal(root.get("status"), status));
                }
                Predicate statusesP = query.where(criteriaBuilder.or(statusesPredicates.toArray(Predicate[]::new))).getRestriction();
                predicates.add(statusesP);
            }
            if (value != null) {
                List<Predicate> textPredicates = new ArrayList<>();
                textPredicates.add(criteriaBuilder.equal(root.get("codeName"), value));
                textPredicates.add(criteriaBuilder.equal(root.get("name"), value));
                Predicate textP = query.where(criteriaBuilder.or(textPredicates.toArray(Predicate[]::new))).getRestriction();
                predicates.add(textP);
            }
            if (predicates.isEmpty()) {
                return query.where().getRestriction();
            }
            return query.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new))).getRestriction();
        });
    }

}
