package com.sparta.week4hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Week4HwApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week4HwApplication.class, args);
    }

}
