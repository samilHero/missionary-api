package com.samill.missionary_backend.missionary.board.module;

import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.exception.MissionaryBoardException;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface MissionaryBoardModule {

    String createMissionaryBoard(@NonNull String memberId, @NonNull CreateMissionaryBoardCommand command) throws MissionaryException;

    MissionaryBoard getMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId) throws MissionaryBoardException;

    Page<MissionaryBoard> getMissionaryBoards(@NonNull String memberId, @NonNull GetMissionaryBoardsQuery query) throws MissionaryBoardException;

    void updateBoard(@NonNull String memberId, @NonNull UpdateMissionaryBoardCommand command) throws MissionaryBoardException;

    void deleteMissionaryBoard(@NonNull String memberId, @NonNull DeleteMissionaryBoardCommand command) throws MissionaryBoardException;

}
