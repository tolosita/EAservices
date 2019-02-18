package com.poli.edu.EAappBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EAappBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(EAappBackApplication.class, args);
    }
}
