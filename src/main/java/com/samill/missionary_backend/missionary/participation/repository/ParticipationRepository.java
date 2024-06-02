package com.samill.missionary_backend.missionary.participation.repository;

import com.samill.missionary_backend.missionary.participation.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, String>, ParticipationCustomRepository {
    List<Participation> findByMissionaryId(String missionaryId);
    Participation findByIdAndUserIdAndMissionaryId(String id, String userId, String missionaryId);
    Participation findByUserIdAndMissionaryId(String userId, String missionaryId);
}
