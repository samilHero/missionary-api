package com.samill.missionary_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;


@TestConfiguration
public class DataJpaTestConfig {

    @PersistenceContext
    EntityManager em;
}
