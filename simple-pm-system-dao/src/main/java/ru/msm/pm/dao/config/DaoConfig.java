package ru.msm.pm.dao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.msm.pm.model.ModelConfig;


@Configuration
@EnableJpaRepositories(basePackages = "ru.msm.pm.dao")
@Import(ModelConfig.class)
public class DaoConfig {

}
