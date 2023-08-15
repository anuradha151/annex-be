package com.anuradha.annexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class AnnexServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnexServiceApplication.class, args);
    }

}
