package com.samill.missionary_backend.missionary.team.repository;

import com.samill.missionary_backend.missionary.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, String>{
    List<Team> findByMissionaryId(String missionaryId);
}
