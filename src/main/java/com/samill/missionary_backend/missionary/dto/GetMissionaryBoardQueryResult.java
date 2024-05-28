package com.samill.missionary_backend.missionary.dto;

import java.util.List;
import lombok.NonNull;

public record GetMissionaryBoardQueryResult(
    @NonNull
    String id,
    @NonNull
    String title,
    @NonNull
    String content,
    @NonNull
    List<GetMissionaryBoardsQueryResultMissionaryBoardFile> files
) {

}
