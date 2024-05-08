package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record CreateMissionaryCommandPoster(

    @NonNull String name,
    @NonNull String path
) {

}
