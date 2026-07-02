package com.golgan.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Task5Application {
    public static void main(String[] args) {
        SpringApplication.run(Task5Application.class, args);
    }
}
