package ru.msm.pm.dao.impls;

import ru.msm.pm.dao.MemberDao;
import ru.msm.pm.model.Employee;

import java.io.*;
import java.util.*;
//todo переделать: задавать пути к файлам в конструкторе; атомарность nextId()
public class EmployeeFileDaoImpl implements MemberDao<Employee, Long> {

    private final String commonDaoDataPath = "simple-pm-system-dao/src/main/java/ru/msm/pm/dao/data/";
    private final String employeeDataFilePath = commonDaoDataPath + "employee";
    private final String idPropsFilePath = commonDaoDataPath + "id.properties";
    private final String idPropName = "id.employee.cur.value";
    private Map<Long, Employee> employees;

    public EmployeeFileDaoImpl() {
        employees = new HashMap<>();
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
        Properties fileDaoProps = new Properties();
        long id;
        try (FileInputStream fis = new FileInputStream(idPropsFilePath)) {
            fileDaoProps.load(fis);
            String property = fileDaoProps.getProperty(idPropName);
            if (property == null) {
                throw new Exception("There is no property with name \"" + idPropName + "\".");
            }
            if (property.equals("")) {
                throw new Exception("There is no value for property with name \"" + idPropName + "\".");
            }
            id = Long.parseLong(property);
            fileDaoProps.setProperty(idPropName, String.valueOf(id + 1));
            try (FileOutputStream fos = new FileOutputStream(idPropsFilePath)) {
                fileDaoProps.store(fos, "set next id");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Property file not found.");
            throw e;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        }
        return id;
    }

}
