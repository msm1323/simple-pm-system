package ru.msm.pm.dao.impls.jdbc_dao;

import lombok.Data;
import ru.msm.pm.common.enums.EmployeeStatus;
import ru.msm.pm.common.enums.ProjectRole;

@Data
public class EmployeeFilter {
    private EmployeeStatus status;
    private Long projectId;
    private ProjectRole role;
}
