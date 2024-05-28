package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record DeleteMissionaryBoardCommand(
    @NonNull
    String id
) {

}
