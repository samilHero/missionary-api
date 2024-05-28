package com.samill.missionary_backend.missionary.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record CreateMissionaryCommand(
    @NonNull
    String name,
    @NonNull
    OffsetDateTime startDate,
    @NonNull
    OffsetDateTime workEndDate,
    @NonNull
    String pastorName,
    @NonNull
    String pastorPhone

) {


}
