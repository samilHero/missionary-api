package com.samill.missionary_backend.participation.infrastructure.repository;

import com.samill.missionary_backend.participation.infrastructure.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, String> {
}
