package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record UpdateMissionaryBoardCommandMissionaryBoardFile(
    String id,
    @NonNull
    String name,
    @NonNull
    String path
) {

    public boolean hasId() {
        return id != null;
    }

    public boolean hasNotId() {
        return !hasId();
    }

}
