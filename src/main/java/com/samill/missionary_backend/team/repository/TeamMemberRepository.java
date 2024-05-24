package com.samill.missionary_backend.team.repository;

import com.samill.missionary_backend.team.entity.Team;
import com.samill.missionary_backend.team.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, String>{
    TeamMember deleteByMissionaryIdAndUserId(String missionaryId, String userId);
}
