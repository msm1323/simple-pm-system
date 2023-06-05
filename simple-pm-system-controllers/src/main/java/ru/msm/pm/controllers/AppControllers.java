package ru.msm.pm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class AppControllers {

    public static void main(String[] args) {
        SpringApplication.run(AppControllers.class, args);
    }

}
