package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record GetMissionaryBoardsQueryResultMissionaryBoardFile(
    @NonNull
    String id,
    @NonNull
    String name,
    @NonNull
    String path
) {

}
