package com.samill.missionary_backend.missionary.board.module;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface MissionaryBoardModule {

    @NonNull String createMissionaryBoard(@NonNull String memberId, @NonNull CreateMissionaryBoardCommand command) throws CommonException;

    @NonNull MissionaryBoard getMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId) throws CommonException;

    @NonNull Page<MissionaryBoard> getMissionaryBoards(@NonNull String memberId, @NonNull GetMissionaryBoardsQuery query) throws CommonException;

    void updateBoard(@NonNull String memberId, @NonNull UpdateMissionaryBoardCommand command) throws CommonException;

    void deleteMissionaryBoard(@NonNull String memberId, @NonNull DeleteMissionaryBoardCommand command) throws CommonException;

}
