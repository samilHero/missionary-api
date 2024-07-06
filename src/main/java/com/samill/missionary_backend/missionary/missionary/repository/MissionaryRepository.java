package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionaryRepository extends JpaRepository<Missionary, String>, MissionaryRepositoryCustom {

    List<Missionary> findAllByDetail_ParticipationPeriod_EndDateLessThanEqual(@NonNull OffsetDateTime date);

    Page<Missionary> findByRegion_IdOrderByPeriod_EndDateDesc(@NonNull String regionId, Pageable pageable);
    
}
