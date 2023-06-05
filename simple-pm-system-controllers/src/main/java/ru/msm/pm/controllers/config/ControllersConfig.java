package ru.msm.pm.controllers.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ru.msm.pm.model")
@EnableJpaRepositories(basePackages = "ru.msm.pm.dao")
@ComponentScan(basePackages = "ru.msm.pm.services")
public class ControllersConfig {        //todo

}
