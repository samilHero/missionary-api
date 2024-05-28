package com.samill.missionary_backend.missionary.staff.repository;

import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionaryStaffRepository extends JpaRepository<MissionaryStaff, String> {

    Optional<MissionaryStaff> findByMissionary_IdAndUserId(String missionaryId, String userId);
}
