package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import lombok.NonNull;

public record GetMissionaryBoardsQuery(
    @NonNull
    String missionaryId,
    @NonNull
    MissionaryBoardType type,
    Integer pageNumber,
    Integer pageSize
) {

    @Override
    public Integer pageNumber() {
        return pageNumber == null ? 0 : pageNumber;
    }

    @Override
    public Integer pageSize() {
        return pageSize == null ? 20 : pageSize;
    }

}
