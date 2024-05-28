package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import java.util.List;
import lombok.NonNull;

public record GetMissionaryBoardsQueryResultMissionaryBoard(
    @NonNull
    String id,
    @NonNull
    MissionaryBoardType type,
    @NonNull
    String title,
    @NonNull
    String content,
    @NonNull
    List<GetMissionaryBoardsQueryResultMissionaryBoardFile> files
) {

}
