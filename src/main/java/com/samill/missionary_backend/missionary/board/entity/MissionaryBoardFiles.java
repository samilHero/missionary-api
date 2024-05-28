package com.samill.missionary_backend.missionary.board.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@Embeddable
public class MissionaryBoardFiles {

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionaryBoardFile> files = new ArrayList<>();

    public MissionaryBoardFiles(List<MissionaryBoardFile> files) {
        this.files = List.copyOf(files);
    }


    public void changeFiles(List<String> removeTargetIds, MissionaryBoardFiles newFiles) {
        removeFilesNotInIds(removeTargetIds);
        addFiles(newFiles);
    }

    void removeFilesNotInIds(List<String> ids) {
        this.files.removeIf(file -> !ids.contains(file.getId()));
    }

    void addFiles(MissionaryBoardFiles files) {
        this.files.addAll(files.files);
    }

    List<String> getIds() {
        return this.files.stream().map(MissionaryBoardFile::getId).toList();
    }

    public List<MissionaryBoardFile> getFiles() {
        return this.files;
    }


}
