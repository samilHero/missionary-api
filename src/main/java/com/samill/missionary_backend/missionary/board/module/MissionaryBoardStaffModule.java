package com.samill.missionary_backend.missionary.board.module;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.exception.AccessDeniedMissionaryBoardException;
import com.samill.missionary_backend.missionary.board.exception.MissionaryBoardException;
import com.samill.missionary_backend.missionary.board.service.MissionaryBoardService;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import com.samill.missionary_backend.missionary.staff.service.MissionaryStaffService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MissionaryBoardStaffModule implements MissionaryBoardModule {

    private final MissionaryService missionaryService;
    private final MissionaryStaffService missionaryStaffService;
    private final MissionaryBoardService missionaryBoardService;
    private final MemberExternalService memberExternalService;

    @Override
    public @NonNull String createMissionaryBoard(@NonNull String memberId, @NonNull CreateMissionaryBoardCommand command) throws CommonException {
        checkMissionaryStaff(command.missionaryId(), memberExternalService.getUserByMemberId(memberId).id());

        final var missionary = missionaryService.getMissionary(command.missionaryId());

        return missionaryBoardService.createMissionaryBoard(missionary, command).getId();
    }

    @Override
    public @NonNull MissionaryBoard getMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId) throws CommonException {
        final var missionaryBoard = missionaryBoardService.getMissionaryBoard(missionaryBoardId);

        checkMissionaryStaff(missionaryBoard.getMissionaryId(), memberExternalService.getUserByMemberId(memberId).id());

        return missionaryBoard;

    }

    @Override
    public @NonNull Page<MissionaryBoard> getMissionaryBoards(@NonNull String memberId, @NonNull GetMissionaryBoardsQuery query)
        throws CommonException {

        checkMissionaryStaff(query.missionaryId(), memberExternalService.getUserByMemberId(memberId).id());

        return missionaryBoardService.getMissionaryBoards(query);
    }

    @Override
    public void updateBoard(@NonNull String memberId, @NonNull UpdateMissionaryBoardCommand command) throws CommonException {
        final var missionaryBoard = missionaryBoardService.getMissionaryBoard(command.id());

        checkMissionaryStaff(missionaryBoard.getMissionaryId(), memberExternalService.getUserByMemberId(memberId).id());

        missionaryBoardService.updateMissionaryBoard(command);
    }

    @Override
    public void deleteMissionaryBoard(@NonNull String memberId, @NonNull DeleteMissionaryBoardCommand command) throws MissionaryBoardException {
        missionaryBoardService.deleteMissionaryBoard(command);
    }


    void checkMissionaryStaff(String missionaryId, String memberId) throws AccessDeniedMissionaryBoardException {
        if (!missionaryStaffService.isExistedMissionaryStaff(missionaryId, memberId)) {
            throw new AccessDeniedMissionaryBoardException();
        }
    }

}
