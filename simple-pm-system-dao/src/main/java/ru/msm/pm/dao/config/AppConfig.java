package ru.msm.pm.dao.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@EntityScan(basePackages = "ru.msm.pm.model")
//@ComponentScan(basePackages = "ru.msm.pm.dao")
//@PropertySource("file:simple-pm-system-dao/src/main/resources/application.properties")
//@EnableAutoConfiguration
//@SpringBootApplication(scanBasePackages="ru.msm.pm.dao")
public class AppConfig {

//    @Value("${pgs.serverName}")
//    private String serverName;
//    @Value("${pgs.user}")
//    private String user;
//    @Value("${pgs.password}")
//    private String password;
//    @Value("${pgs.dbName}")
//    private String dbName;
//    @Value("${pgs.port}")
//    private int port;
//
//    @Bean
//    public DataSource dataSource(){
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setServerNames(new String[]{serverName});
//        dataSource.setUser(user);
//        dataSource.setPassword(password);
//        dataSource.setDatabaseName(dbName);
//        dataSource.setPortNumbers(new int[]{port});
//        return dataSource;
//    }


}
