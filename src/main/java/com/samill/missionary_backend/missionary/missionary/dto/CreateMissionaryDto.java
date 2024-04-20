package com.samill.missionary_backend.missionary.missionary.dto;

import java.time.LocalDateTime;
import lombok.NonNull;

public record CreateMissionaryDto(
    @NonNull
    LocalDateTime startDate,
    @NonNull
    LocalDateTime endDate,
    @NonNull
    Integer price,
    @NonNull
    String pastorName,
    @NonNull
    String pastorPhone,
    @NonNull
    String posterName,

    @NonNull
    String posterUrl
) {

}
