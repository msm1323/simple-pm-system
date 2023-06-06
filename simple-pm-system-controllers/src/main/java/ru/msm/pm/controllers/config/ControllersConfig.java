package ru.msm.pm.controllers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "ru.msm.pm.controllers")
@Import(SwaggerConfig.class)
public class ControllersConfig {

}
