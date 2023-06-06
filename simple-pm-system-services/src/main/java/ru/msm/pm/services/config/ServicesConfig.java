package ru.msm.pm.services.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.msm.pm.common.config.CommonConfig;
import ru.msm.pm.dao.config.DaoConfig;


@Configuration
@ComponentScan(basePackages = "ru.msm.pm.services")
@Import({CommonConfig.class, DaoConfig.class})
public class ServicesConfig {

}
