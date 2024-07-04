package com.samill.missionary_backend.missionary.dto;

import java.util.List;
import lombok.NonNull;

public record GetMissionariesByRegionQueryResult(
    @NonNull List<GetMissionariesByRegionQueryResultMissionary> missionaries,
    int totalCount,
    int totalPages,
    int currentPage
) {

}
