package com.samill.missionary_backend.participation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Participant {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private LocalDate birthday;
}
