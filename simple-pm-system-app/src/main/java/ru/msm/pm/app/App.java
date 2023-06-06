package ru.msm.pm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.msm.pm.common.config.CommonConfig;
import ru.msm.pm.controllers.config.ControllersConfig;
import ru.msm.pm.controllers.config.SecurityConfig;
import ru.msm.pm.services.config.ServicesConfig;

@SpringBootApplication
@Import({CommonConfig.class, ServicesConfig.class, SecurityConfig.class, ControllersConfig.class})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
