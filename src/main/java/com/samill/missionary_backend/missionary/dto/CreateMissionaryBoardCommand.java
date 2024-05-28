package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import java.util.List;
import lombok.NonNull;


public record CreateMissionaryBoardCommand(
    
    @NonNull
    String missionaryId,
    @NonNull
    String title,
    @NonNull
    String content,
    @NonNull
    MissionaryBoardType type,
    List<CreateMissionaryBoardCommandMissionaryBoardFile> files
) {

    @Override
    public List<CreateMissionaryBoardCommandMissionaryBoardFile> files() {
        return files == null ? List.of() : files;
    }
}
