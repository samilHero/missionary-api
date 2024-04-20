package com.samill.missionary_backend.participation.entity;

import com.samill.missionary_backend.missionary.Missionary;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Participation {
    @Id
    @UuidGenerator
    private UUID id;
    @ManyToOne
    private Missionary missionary;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private Participant participant;
    private int applyFee;
    private boolean isPaid;
}
