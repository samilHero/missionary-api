package com.samill.missionary_backend.missionary.dto;

import java.util.List;
import lombok.NonNull;

public record UpdateMissionaryBoardCommand(

    @NonNull
    String id,
    @NonNull
    String title,
    @NonNull
    String content,
    List<UpdateMissionaryBoardCommandMissionaryBoardFile> files
) {

    @Override
    public List<UpdateMissionaryBoardCommandMissionaryBoardFile> files() {
        return files == null ? List.of() : files;
    }


    public List<UpdateMissionaryBoardCommandMissionaryBoardFile> newFiles() {
        return files.stream().filter(UpdateMissionaryBoardCommandMissionaryBoardFile::hasNotId).toList();
    }


    public List<String> keepFileIds() {
        return files.stream()
            .filter(UpdateMissionaryBoardCommandMissionaryBoardFile::hasId)
            .map(UpdateMissionaryBoardCommandMissionaryBoardFile::id)
            .toList();
    }

}
