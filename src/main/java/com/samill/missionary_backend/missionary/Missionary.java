package com.samill.missionary_backend.missionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Missionary {
    @Id
    @UuidGenerator
    private UUID id;
}
