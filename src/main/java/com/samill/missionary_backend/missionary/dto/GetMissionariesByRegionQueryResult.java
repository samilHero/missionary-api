package com.samill.missionary_backend.missionary.dto;

import java.util.List;

public record GetMissionariesByRegionQueryResult(
    List<GetMissionariesByRegionQueryResultMissionary> getMissionariesByRegionQueryResultMissionary,

    int totalCount
) {

}
