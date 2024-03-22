package com.samill.missionary_backend;

import com.samill.missionary_backend.participation.ParticipationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ConfigurationPropertiesScan
public class MissionaryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.samill.missionary_backend.MissionaryBackendApplication.class, args);
    }

}
