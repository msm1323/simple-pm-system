package ru.msm.pm.dao.impls.jdbc_dao;

import org.springframework.stereotype.Repository;
import ru.msm.pm.dao.MemberDao;
import ru.msm.pm.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

@Repository
public class EmployeeJdbcDaoImpl implements MemberDao<Employee, Long> { //todo draft

    @PersistenceContext
    private EntityManager entityManager;

//    private final DataSource dataSource;

//    @Autowired
//    public EmployeeJdbcDaoImpl(DataSource dataSource) throws IOException {
//        this.dataSource = dataSource;
//    }

    @Override
    public Employee create(Employee employee) throws SQLException {
        String insertQuery = "INSERT INTO employee (name, surname, status, patronymic, account, position, email)" +
                " values (:name, :surname, :status, :patronymic, :account, :position, :email) RETURNING id";
        Long id = ((BigInteger) getQuery(employee, insertQuery).getSingleResult()).longValue();
        employee.setId(id);
        return employee;
    }

    @Override
    public Employee update(Employee employee) throws SQLException {
        String updateQuery = "UPDATE employee set name=:name, surname=:surname, status=:status, patronymic=:patronymic," +
                " account=:account, position=:position, email=:email WHERE id=:id";
        int i = getQuery(employee, updateQuery).executeUpdate();
        if (i == 0) {
            throw new SQLException("Failed to change employee in the table \"employee\"!");
        }
        return employee;
    }

    @Override
    public Employee getById(Long id) {
        String selectQuery = "SELECT * FROM employee WHERE id = :id";
        return entityManager.createQuery("select e from Employee e where e.id=:id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Employee deleteById(Long id) throws SQLException {
//        Employee employee;
//        String deleteQuery = "DELETE FROM employee WHERE id = " + id + " RETURNING *;";
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            try (ResultSet rs = statement.executeQuery(deleteQuery)) {
//                if (!rs.next()) {
//                    throw new SQLException("Failed to delete employee with id=" + id + " in the table \"employee\"!");
//                }
//                employee = buildEmployeeByRs(rs);
//            }
//        }
//        return employee;
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
//        String selectQuery = "SELECT * FROM employee";
//        List<Employee> employees = new LinkedList<>();
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            try (ResultSet rs = statement.executeQuery(selectQuery)) {
//                if (!rs.next()) {
//                    throw new SQLException("Failed to find employees в in the table \"employee\"!");
//                }
//                employees.add(buildEmployeeByRs(rs));
//                while (rs.next()) {
//                    employees.add(buildEmployeeByRs(rs));
//                }
//            }
//        }
//        return employees;
        return null;
    }

    public List<Employee> search(EmployeeFilter filter) throws SQLException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        TypedQuery<Employee> employee;

//        String joinRole_roleCondition = "", joinPp = "";
//        String ppCondition = "", statusCondition = "";
//        Map<Integer, Object> paramMap = new HashMap<>();
//        int paramIndex = 1;
//        if (filter.getProjectId() != null) {
//            joinPp = " JOIN project_participant pp on employee.id = pp.employee";
//            ppCondition = " AND pp.project=?";
//            paramMap.put(paramIndex++, filter.getProjectId());
//        }
//        if (filter.getRole() != null) {
//            joinPp = " JOIN project_participant pp on employee.id = pp.employee";
//            joinRole_roleCondition = " JOIN role on pp.role = role.id AND role.role=?";
//            paramMap.put(paramIndex++, filter.getRole().toString());
//        }
//        if (filter.getStatus() != null) {
//            statusCondition = joinPp.equals("") ? " WHERE employee.status=?" : " AND employee.status=?";
//            paramMap.put(paramIndex++, filter.getStatus().toString());
//        }
//        String searchQuery = "SELECT employee.* FROM employee" +
//                joinPp + ppCondition +
//                joinRole_roleCondition + statusCondition;
//        List<Employee> employees;
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement ps = connection.prepareStatement(searchQuery)) {
//            employees = new LinkedList<>();
//            for (Map.Entry<Integer, Object> entry : paramMap.entrySet()) {
//                ps.setObject(entry.getKey(), entry.getValue());
//            }
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    employees.add(buildEmployeeByRs(rs));
//                }
//            }
//        }
//        return employees;
        return null;
    }

    private Query getQuery(Employee employee, String query) throws SQLException {
        return entityManager.createNativeQuery(query)
                .setParameter("name", employee.getName())
                .setParameter("surname", employee.getSurname())
                .setParameter("status", employee.getStatus().toString())
                .setParameter("patronymic", employee.getPatronymic())
                .setParameter("account", employee.getAccount())
                .setParameter("position", employee.getPosition())
                .setParameter("email", employee.getEmail());
    }

    private Employee buildEmployeeByRs(ResultSet rs) throws SQLException {
        return Employee.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .surname(rs.getString(3))
//                .status(EmployeeStatus.valueOf(rs.getString(4)))
                .patronymic(rs.getString(5))
                .account(rs.getString(6))
                .position(rs.getString(7))
                .email(rs.getString(8)).build();
    }

}
