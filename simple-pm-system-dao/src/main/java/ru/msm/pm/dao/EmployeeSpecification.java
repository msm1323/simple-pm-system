package ru.msm.pm.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import ru.msm.pm.dao.impls.jdbc_dao.EmployeeFilter;
import ru.msm.pm.common.enums.EmployeeStatus;
import ru.msm.pm.model.Employee;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification {

    // todo draft; Specification для фильтра с полями из разных классов??
    public static Specification<Employee> getSpec(EmployeeFilter filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!ObjectUtils.isEmpty(filter.getStatus())) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }
            if ((CollectionUtils.isEmpty(predicates))) {
                return query.where().getRestriction();
            }
            return query.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new))).getRestriction();
        });
    }

    /* Поиск осуществляется по текстовому значению,
     которое проверяется по атрибутам Фамилия, Имя, Отчество, учетной записи, адресу электронной почты
     и только среди активных сотрудников. */
    public static Specification<Employee> activeAndTextEqualValue(String value) {   //todo динамический
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> textPredicates = new ArrayList<>();
            if (value.isEmpty()) {
                return query.where().getRestriction();
            }
            textPredicates.add(criteriaBuilder.equal(root.get("surname"), value));
            textPredicates.add(criteriaBuilder.equal(root.get("name"), value));
            textPredicates.add(criteriaBuilder.equal(root.get("patronymic"), value));
            textPredicates.add(criteriaBuilder.equal(root.get("account"), value));
            textPredicates.add(criteriaBuilder.equal(root.get("email"), value));

            Predicate textP = query.where(criteriaBuilder.or(textPredicates.toArray(Predicate[]::new))).getRestriction();
            Predicate statusP = criteriaBuilder.equal(root.get("status"), EmployeeStatus.ACTIVE);

            List<Predicate> predicates2 = new ArrayList<>(2);
            predicates2.add(statusP);
            predicates2.add(textP);

            return query.where(criteriaBuilder.and(predicates2.toArray(Predicate[]::new))).getRestriction();
        });
    }

}
