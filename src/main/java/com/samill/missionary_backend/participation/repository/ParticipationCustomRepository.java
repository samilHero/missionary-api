package com.samill.missionary_backend.participation.repository;

import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;

import java.util.List;

public interface ParticipationCustomRepository {
    List<GetParticipationQueryResult> findAllByConditionOrderByCreatedAtAsc(GetParticipationsQuery getParticipationsQuery);

}
