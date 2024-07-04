package com.samill.missionary_backend.missionary.region.dto;

import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import lombok.NonNull;

public record CreateMissionaryRegionDto(
    @NonNull String name,
    @NonNull MissionaryRegionType type
) {

}
