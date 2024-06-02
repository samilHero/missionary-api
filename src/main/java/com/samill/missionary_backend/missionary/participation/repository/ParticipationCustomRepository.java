package com.samill.missionary_backend.missionary.participation.repository;

import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipationCustomRepository {
//    List<GetParticipationQueryResult> findAllByConditionOrderByCreatedAtAsc(GetParticipationsQuery getParticipationsQuery);
    Page<GetParticipationQueryResult> findAllByQuery(String missionaryId, GetParticipationsQuery getParticipationsQuery, Pageable pageable);
}
