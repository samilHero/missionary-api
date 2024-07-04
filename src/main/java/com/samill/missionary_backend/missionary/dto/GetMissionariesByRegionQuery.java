package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public class GetMissionariesByRegionQuery {

    public final @NonNull String regionId;
    public final int pageSize;
    public final int pageNumber;

    public GetMissionariesByRegionQuery(@NonNull String regionId, Integer pageSize, Integer pageNumber) {
        this.regionId = regionId;
        this.pageSize = pageSize == null ? 10 : pageSize;
        this.pageNumber = pageNumber == null ? 1 : pageNumber;
    }
}
