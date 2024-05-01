package com.samill.missionary_backend.participation.repository;

import com.samill.missionary_backend.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, String> {
    List<Participation> findByMissionaryId(String missionaryId);
}
