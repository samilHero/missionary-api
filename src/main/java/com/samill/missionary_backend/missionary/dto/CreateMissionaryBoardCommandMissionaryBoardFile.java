package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record CreateMissionaryBoardCommandMissionaryBoardFile(
    @NonNull
    String name,
    @NonNull
    String path
) {

}
