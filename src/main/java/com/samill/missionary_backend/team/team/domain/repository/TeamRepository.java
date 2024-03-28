package com.samill.missionary_backend.team.team.domain.repository;

import com.samill.missionary_backend.team.team.domain.entity.TeamEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, String> {

    List<TeamEntity> findAllByMissionaryId(String missionaryId);


}
