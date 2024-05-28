package com.samill.missionary_backend.missionary.dto;

import java.util.List;
import lombok.NonNull;

public record GetMissionaryBoardsQueryResult(
    @NonNull
    List<GetMissionaryBoardsQueryResultMissionaryBoard> boards,
    @NonNull
    Integer totalPageCount,

    @NonNull
    Integer totalCount
) {

}
