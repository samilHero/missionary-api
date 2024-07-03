package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionaryRepository extends JpaRepository<Missionary, String> {

    List<Missionary> findAllByDetail_ParticipationPeriod_EndDateLessThanEqual(OffsetDateTime date);

    List<Missionary> findAllByMissionaryStaffs_UserIdAndPeriod_EndDateGreaterThanEqual(String userId, OffsetDateTime now);
}
