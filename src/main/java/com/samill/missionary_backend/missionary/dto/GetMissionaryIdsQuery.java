package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

import java.time.OffsetDateTime;

public record GetMissionaryIdsQuery (
        @NonNull OffsetDateTime endDate
){
}
