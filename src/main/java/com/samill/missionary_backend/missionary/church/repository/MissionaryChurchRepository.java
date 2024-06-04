package com.samill.missionary_backend.missionary.church.repository;

import com.samill.missionary_backend.missionary.church.entity.MissionaryChurch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionaryChurchRepository extends JpaRepository<MissionaryChurch, String> {


}
