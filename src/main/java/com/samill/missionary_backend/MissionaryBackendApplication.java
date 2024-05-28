package com.samill.missionary_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAspectJAutoProxy
@EnableScheduling
public class MissionaryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.samill.missionary_backend.MissionaryBackendApplication.class, args);
    }

}
