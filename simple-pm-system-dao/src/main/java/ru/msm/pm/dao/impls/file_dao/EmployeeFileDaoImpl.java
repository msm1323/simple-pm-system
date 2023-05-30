package ru.msm.pm.dao.impls.file_dao;

import ru.msm.pm.dao.MemberDao;
import ru.msm.pm.model.Employee;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeFileDaoImpl implements MemberDao<Employee, Long> {

    private static EmployeeFileDaoImpl employeeFileDao;
    private final AtomicLong idGenerator;
    private final String commonDaoDataPath = "simple-pm-system-dao/src/main/java/ru/msm/pm/dao/data/";
    private final String employeeDataFilePath = commonDaoDataPath + "employee";
    private final String idPropsFilePath = commonDaoDataPath + "id.properties";
    private final String idPropName = "id.employee.cur.value";
    private Map<Long, Employee> employees;

    private EmployeeFileDaoImpl() throws Exception {
        employees = new HashMap<>();
        idGenerator = new AtomicLong(Long.parseLong(FileDaoUtils.getProperty(idPropsFilePath, idPropName)));
    }

    public static EmployeeFileDaoImpl getInstance() throws Exception {
        if (employeeFileDao == null) {
            employeeFileDao = new EmployeeFileDaoImpl();
        }
        return employeeFileDao;
    }

    @Override
    public Employee create(Employee employee) throws Exception {
        employee.setId(nextId());
        update(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) throws Exception {
        getEmployees().put(employee.getId(), employee);
        writeData();
        return employee;
    }

    @Override
    public Employee getById(Long id) throws Exception {
        return getEmployees().get(id);
    }

    @Override
    public Employee deleteById(Long id) throws Exception {
        Employee removed = getEmployees().remove(id);
        writeData();
        return removed;
    }

    @Override
    public List<Employee> getAll() throws Exception {
        return new ArrayList<>(getEmployees().values());
    }

    private Map<Long, Employee> getEmployees() throws Exception {
        if (employees.size() == 0) {
            readData();
        }
        return employees;
    }

    private void writeData() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(employeeDataFilePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)) {
            out.writeObject(employees);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private void readData() throws Exception {
        File employeeData = new File(employeeDataFilePath);
        if (employeeData.exists() && !employeeData.isFile()) {
            String isDir = employeeData.isDirectory() ? " It's a directory." : "";
            throw new IOException(employeeDataFilePath + " is not a normal file." + isDir);
        }
        if (!employeeData.exists() & employees.size() != 0) {
            throw new Exception();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(employeeDataFilePath))) {
            employees = (HashMap<Long, Employee>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (IOException e) {
            System.err.println("Error initializing stream. Probably the source file is empty.");
            e.printStackTrace();
        }
    }

    private Long nextId() throws Exception {
        long id = idGenerator.incrementAndGet();
        FileDaoUtils.setProperty(idPropsFilePath, idPropName, String.valueOf(id));
        return id;
    }

}
