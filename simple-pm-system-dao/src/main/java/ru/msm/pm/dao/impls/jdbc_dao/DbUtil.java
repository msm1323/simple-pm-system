package ru.msm.pm.dao.impls.jdbc_dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

public class DbUtil {

    private PGSimpleDataSource dataSource;
    private String propFilePath = "simple-pm-system-dao/src/main/java/ru/msm/pm/dao/db/postgres.properties";
    private String serverName;
    private String user;
    private String password;
    private String dbName;
    private int port;

    public DbUtil() throws IOException {
        try (FileInputStream fis = new FileInputStream(propFilePath)) {
            Properties properties = new Properties();
            properties.load(fis);
            this.serverName = properties.getProperty("serverName");
            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");
            this.dbName = properties.getProperty("dbName");
            this.port = Integer.parseInt(properties.getProperty("port"));
        }
    }

    public DataSource getDataSource() {
        if (dataSource == null) {
            buildDataSource();
        }
        return dataSource;
    }

    private void buildDataSource() {
        dataSource = new PGSimpleDataSource();
        dataSource.setServerName(serverName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDatabaseName(dbName);
        dataSource.setPortNumber(port);
//        dataSource.getConnection().close();
    }
}
