package com.samill.missionary_backend.missionary.dto;

import java.util.List;
import lombok.NonNull;

public record GetMissionaryRegionsQueryResult(
    @NonNull List<GetMissionaryRegionsQueryResultRegion> domestic,
    @NonNull List<GetMissionaryRegionsQueryResultRegion> abroad
) {

}
