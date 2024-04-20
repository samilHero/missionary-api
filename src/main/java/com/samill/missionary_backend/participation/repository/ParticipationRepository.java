package com.samill.missionary_backend.participation.repository;

import com.samill.missionary_backend.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
}
