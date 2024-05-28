package com.samill.missionary_backend.missionary.board.module;

import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.exception.AccessDeniedMissionaryBoardException;
import com.samill.missionary_backend.missionary.board.exception.MissionaryBoardException;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MissionaryBoardUserModule implements MissionaryBoardModule {

    @Override
    public @NonNull String createMissionaryBoard(@NonNull String memberId, @NonNull CreateMissionaryBoardCommand command)
        throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();
    }

    @Override
    public @NonNull MissionaryBoard getMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId) throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();
    }

    @Override
    public @NonNull Page<MissionaryBoard> getMissionaryBoards(@NonNull String memberId, @NonNull GetMissionaryBoardsQuery query)
        throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();
    }

    @Override
    public void updateBoard(@NonNull String memberId, @NonNull UpdateMissionaryBoardCommand command) throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();
    }

    @Override
    public void deleteMissionaryBoard(@NonNull String memberId, @NonNull DeleteMissionaryBoardCommand command) throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();

    }


}
