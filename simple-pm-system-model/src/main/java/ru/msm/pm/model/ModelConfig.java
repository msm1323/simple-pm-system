package ru.msm.pm.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "ru.msm.pm.model")
public class ModelConfig {



}
