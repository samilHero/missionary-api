package com.samill.missionary_backend.missionary.board.module;

import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.exception.MissionaryBoardException;
import com.samill.missionary_backend.missionary.board.service.MissionaryBoardService;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionaryBoardAdminModule implements MissionaryBoardModule {

    private final MissionaryBoardService missionaryBoardService;
    private final MissionaryService missionaryService;


    @Override
    public @NonNull String createMissionaryBoard(@NonNull String memberId, @NonNull CreateMissionaryBoardCommand command) throws MissionaryException {
        final Missionary missionary = missionaryService.getMissionary(command.missionaryId());
        final MissionaryBoard missionaryBoard = missionaryBoardService.createMissionaryBoard(missionary, command);

        return missionaryBoard.getId();
    }

    @Override
    public @NonNull MissionaryBoard getMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId) throws MissionaryBoardException {
        return missionaryBoardService.getMissionaryBoard(missionaryBoardId);
    }

    @Override
    public @NonNull Page<MissionaryBoard> getMissionaryBoards(@NonNull String memberId, @NonNull GetMissionaryBoardsQuery query)
        throws MissionaryBoardException {
        return missionaryBoardService.getMissionaryBoards(query);
    }


    @Override
    public void updateBoard(@NonNull String memberId, @NonNull UpdateMissionaryBoardCommand command) throws MissionaryBoardException {
        missionaryBoardService.updateMissionaryBoard(command);
    }

    @Override
    public void deleteMissionaryBoard(@NonNull String memberId, @NonNull DeleteMissionaryBoardCommand command) throws MissionaryBoardException {
        missionaryBoardService.deleteMissionaryBoard(command);
    }
}
