package com.samill.missionary_backend.missionary.team.repository;

import com.samill.missionary_backend.missionary.team.dto.GetTeamListQuery;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQueryResult;
import java.util.List;

public interface TeamCustomRepository {

    public List<GetTeamListQueryResult> findAllByQuery(String missionaryId, GetTeamListQuery getTeamListQuery);
}
