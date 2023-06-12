package ru.msm.pm.services;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class BaseTest {

    @Container
    static PostgreSQLContainer<?> psgContainer = new PostgreSQLContainer<>("postgres")
            .withInitScript("db_init.sql");

    @BeforeAll
    static void beforeAll() {
        psgContainer.start();
    }

    @AfterAll
    static void afterAll() {
        psgContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", psgContainer::getJdbcUrl);
        registry.add("spring.datasource.username", psgContainer::getUsername);
        registry.add("spring.datasource.password", psgContainer::getPassword);
    }

}
