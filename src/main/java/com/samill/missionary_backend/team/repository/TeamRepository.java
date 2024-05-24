package com.samill.missionary_backend.team.repository;

import com.samill.missionary_backend.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String>{
}
