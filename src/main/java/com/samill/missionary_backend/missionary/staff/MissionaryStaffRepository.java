package com.samill.missionary_backend.missionary.staff;

import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionaryStaffRepository extends JpaRepository<MissionaryStaff, String> {

    Optional<MissionaryStaff> findByMissionary_IdAndUserId(String missionaryId, String userId);
}
