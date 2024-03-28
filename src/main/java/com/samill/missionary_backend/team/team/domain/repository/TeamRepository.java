package com.samill.missionary_backend.team.team.domain.repository;

import com.samill.missionary_backend.team.team.infrastructure.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, String> {

    List<TeamEntity> findAllByMissionaryId(String missionaryId);


}
