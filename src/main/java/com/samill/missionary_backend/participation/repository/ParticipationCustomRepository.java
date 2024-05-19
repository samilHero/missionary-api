package com.samill.missionary_backend.participation.repository;

import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipationCustomRepository {
//    List<GetParticipationQueryResult> findAllByConditionOrderByCreatedAtAsc(GetParticipationsQuery getParticipationsQuery);
    Page<GetParticipationQueryResult> findAllByQuery(GetParticipationsQuery getParticipationsQuery, Pageable pageable);
}
