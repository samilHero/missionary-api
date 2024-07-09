package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionaryRepository extends JpaRepository<Missionary, String> {

    List<Missionary> findAllByDetail_ParticipationPeriod_EndDateLessThanEqual(@NonNull OffsetDateTime date);

    Page<Missionary> findByRegion_IdOrderByPeriod_EndDateDesc(@NonNull String regionId, Pageable pageable);

    @Query(
        value =
            "SELECT missionary.* "
                + "FROM (SELECT missionary.*, RANK() OVER (PARTITION BY missionary_region_id ORDER BY created_at DESC) as rank FROM missionary) AS missionary "
                + "         LEFT JOIN missionary_staff "
                + "                   on missionary_staff.missionary_id = missionary.id "
                + " WHERE missionary.rank = 1 "
                + "  AND missionary_staff.user_id = ?1",
        nativeQuery = true
    )
    List<Missionary> findTopRankedMissionariesByMissionaryStaffs_User_Id(String userId);
}
