package com.samill.missionary_backend.gateway.dto;

import lombok.NonNull;

public record CreateMissionaryResult(
    @NonNull String id
) {

}
