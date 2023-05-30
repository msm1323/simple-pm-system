package ru.msm.pm.dao.impls.jdbc_dao;

import ru.msm.pm.dao.MemberDao;
import ru.msm.pm.enums.EmployeeStatus;
import ru.msm.pm.model.Employee;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmployeeJdbcDaoImpl implements MemberDao<Employee, Long> {

    private static EmployeeJdbcDaoImpl employeeJdbcDao;
    private final DbUtil dbUtil = new DbUtil();
    private final DataSource dataSource = dbUtil.getDataSource();

    private EmployeeJdbcDaoImpl() throws IOException {
    }

    public static EmployeeJdbcDaoImpl getInstance() throws IOException {
        if (employeeJdbcDao == null) {
            employeeJdbcDao = new EmployeeJdbcDaoImpl();
        }
        return employeeJdbcDao;
    }

    @Override
    public Employee create(Employee employee) throws SQLException {
        String insertQuery = "INSERT INTO employee (name, surname, status, patronymic, account, position, email)" +
                " values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(preparedStatement, employee);
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new SQLException("Failed to add employee into the table \"employee\"!");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    employee.setId(id);
                }
            }
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) throws SQLException {
        String updateQuery = "UPDATE employee set name=?, surname=?, status=?, patronymic=?, account=?, position=?, email=?" +
                " WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(preparedStatement, employee);
            preparedStatement.setLong(8, employee.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new SQLException("Failed to change employee in the table \"employee\"!");
            }
        }
        return employee;
    }

    @Override
    public Employee getById(Long id) throws SQLException {
        Employee employee;
        String selectQuery = "SELECT * FROM employee WHERE id = " + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectQuery)) {
                if (!rs.next()) {
                    throw new SQLException("Failed to find employee with id=\" + id + \" in the table \"employee\"!");
                }
                employee = buildEmployeeByRs(rs);
            }
        }
        return employee;
    }

    @Override
    public Employee deleteById(Long id) throws SQLException {
        Employee employee;
        String deleteQuery = "DELETE FROM employee WHERE id = " + id + " RETURNING *;";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(deleteQuery)) {
                if (!rs.next()) {
                    throw new SQLException("Failed to delete employee with id=" + id + " in the table \"employee\"!");
                }
                employee = buildEmployeeByRs(rs);
            }
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        String selectQuery = "SELECT * FROM employee";
        List<Employee> employees = new LinkedList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectQuery)) {
                if (!rs.next()) {
                    throw new SQLException("Failed to find employees в in the table \"employee\"!");
                }
                employees.add(buildEmployeeByRs(rs));
                while (rs.next()) {
                    employees.add(buildEmployeeByRs(rs));
                }
            }
        }
        return employees;
    }

    public List<Employee> search(EmployeeFilter filter) throws SQLException {
        String joinRole_roleCondition = "", joinPp = "";
        String ppCondition = "", statusCondition = "";
        Map<Integer, Object> paramMap = new HashMap<>();
        int paramIndex = 1;
        if (filter.getProjectId() != null) {
            joinPp = " JOIN project_participant pp on employee.id = pp.employee";
            ppCondition = " AND pp.project=?";
            paramMap.put(paramIndex++, filter.getProjectId());
        }
        if (filter.getRole() != null) {
            joinPp = " JOIN project_participant pp on employee.id = pp.employee";
            joinRole_roleCondition = " JOIN role on pp.role = role.id AND role.role=?";
            paramMap.put(paramIndex++, filter.getRole().toString());
        }
        if (filter.getStatus() != null) {
            statusCondition = joinPp.equals("") ? " WHERE employee.status=?" : " AND employee.status=?";
            paramMap.put(paramIndex++, filter.getStatus().toString());
        }
        String searchQuery = "SELECT employee.* FROM employee" +
                joinPp + ppCondition +
                joinRole_roleCondition + statusCondition;
        List<Employee> employees;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(searchQuery)) {
            employees = new LinkedList<>();
            for (Map.Entry<Integer, Object> entry : paramMap.entrySet()) {
                ps.setObject(entry.getKey(), entry.getValue());
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    employees.add(buildEmployeeByRs(rs));
                }
            }
        }
        return employees;   //todo выкидывать ли исключение, если запрос не вернул результатов?
    }

    private void fillPreparedStatement(PreparedStatement preparedStatement, Employee employee) throws SQLException {
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getSurname());
        preparedStatement.setString(3, employee.getStatus().toString());
        preparedStatement.setString(4, employee.getPatronymic());
        preparedStatement.setString(5, employee.getAccount());
        preparedStatement.setString(6, employee.getPosition());
        preparedStatement.setString(7, employee.getEmail());
    }

    private Employee buildEmployeeByRs(ResultSet rs) throws SQLException {
        return Employee.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .surname(rs.getString(3))
                .status(EmployeeStatus.valueOf(rs.getString(4)))
                .patronymic(rs.getString(5))
                .account(rs.getString(6))
                .position(rs.getString(7))
                .email(rs.getString(8)).build();
    }

}
