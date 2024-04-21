package com.samill.missionary_backend.participation.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
//    @ManyToOne
//    private Missionary missionary;
//    @Transient
//    private Participant participant;
    private int applyFee;
    private boolean isPaid;
    private String identificationNumber;
}
