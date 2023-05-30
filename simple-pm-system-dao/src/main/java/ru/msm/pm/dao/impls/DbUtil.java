package ru.msm.pm.dao.impls;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DbUtil {

    public static DataSource buildDataSource(String serverName, String user, String pass, String dbName, int port) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(serverName);
        dataSource.setUser(user);
        dataSource.setPassword(pass);
        dataSource.setDatabaseName(dbName);
        dataSource.setPortNumber(port);
//        dataSource.getConnection().close();
        return dataSource;
    }
}
