package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record GetMissionaryRegionsQueryResultRegion(
    @NonNull String id,
    @NonNull String name
) {

}
