package com.samill.missionary_backend.missionary.board.dto;

import lombok.NonNull;

public record WriteMissionaryBoardDto(
    @NonNull String title,
    @NonNull String content,
    String fileUrl
) {

}