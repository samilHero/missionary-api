package com.samill.missionary_backend.missionary.staff.repository;

import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionaryStaffRepository extends JpaRepository<MissionaryStaff, String> {

    Optional<MissionaryStaff> findByMissionary_IdAndUserId(String missionaryId, String userId);

    List<MissionaryStaff> findAllByUserId(String userId);

    List<MissionaryStaff> findAllByMissionary_Id(String missionaryId);

    void deleteAllByMissionary_IdAndUserIdIn(String missionaryId, List<String> userIds);

    boolean existsByMissionary_IdAndUserId(String missionaryId, String userId);
}
