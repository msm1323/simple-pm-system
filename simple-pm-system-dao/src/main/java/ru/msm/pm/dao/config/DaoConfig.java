package ru.msm.pm.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EntityScan(basePackages = "ru.msm.pm.model")
@EnableJpaRepositories(basePackages = "ru.msm.pm.dao")
public class DaoConfig {

}
