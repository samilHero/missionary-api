package com.samill.missionary_backend.missionary.participation.repository;

import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsDownloadQuery;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipationCustomRepository {

    List<GetParticipationQueryResult> findAllByQueryForCsv(String missionaryId, GetParticipationsDownloadQuery getParticipationsDownloadQuery);

    Page<GetParticipationQueryResult> findAllByQuery(String missionaryId, GetParticipationsQuery getParticipationsQuery, Pageable pageable);
}
