package com.samill.missionary_backend.church.dto;

import lombok.NonNull;

public record CreateMissionaryCommandResult(
    @NonNull String id
) {

}
