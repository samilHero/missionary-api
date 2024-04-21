package com.samill.missionary_backend.missionary.board.dto;

public record UpdateMissionaryBoardDto(
    String title,
    String content,

    String fileUrl
) {

}
