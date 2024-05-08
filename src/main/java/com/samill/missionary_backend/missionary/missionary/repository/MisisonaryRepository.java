package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MisisonaryRepository extends JpaRepository<Missionary, String> {

    @Query("SELECT m FROM Missionary m where m.participationPeriod.startDate <= :date and m.participationPeriod.endDate > :date")
    List<Missionary> findAllInParticipationPeriod(
        @Param("date") OffsetDateTime date
    );
    
}
