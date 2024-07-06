package com.samill.missionary_backend.missionary.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import lombok.NonNull;

public record GetMissionaryRegionsQueryResult(
    @NonNull List<GetMissionaryRegionsQueryResultRegion> domestic,
    @NonNull List<GetMissionaryRegionsQueryResultRegion> abroad
) {

    public @NonNull List<GetMissionaryRegionsQueryResultRegion> regions() {
        return Stream.of(domestic, abroad).flatMap(Collection::stream).toList();
    }
}
