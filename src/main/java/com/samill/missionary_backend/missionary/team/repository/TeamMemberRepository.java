package com.samill.missionary_backend.missionary.team.repository;

import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, String>{
    void deleteByIdInAndUserId(List<String> ids, String userId);
}
