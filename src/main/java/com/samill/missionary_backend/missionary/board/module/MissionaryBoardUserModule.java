package com.samill.missionary_backend.missionary.board.module;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.member.exception.MemberException;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.exception.AccessDeniedMissionaryBoardException;
import com.samill.missionary_backend.missionary.board.exception.MissionaryBoardException;
import com.samill.missionary_backend.missionary.board.service.MissionaryBoardService;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.participation.service.ParticipationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionaryBoardUserModule implements MissionaryBoardModule {

    private final MemberExternalService memberExternalService;

    private final ParticipationService participationService;

    private final MissionaryBoardService missionaryBoardService;


    @Override
    public @NonNull String createMissionaryBoard(@NonNull String memberId, @NonNull CreateMissionaryBoardCommand command)
        throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();
    }

    @Override
    public @NonNull MissionaryBoard getMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId) throws CommonException {
        final var missionaryBoard = missionaryBoardService.getMissionaryBoard(missionaryBoardId);

        checkParticipatingOrThrow(missionaryBoard.getMissionaryId(), getUserIdByMemberId(memberId));

        return missionaryBoard;
    }

    @Override
    public @NonNull Page<MissionaryBoard> getMissionaryBoards(@NonNull String memberId, @NonNull GetMissionaryBoardsQuery query)
        throws CommonException {
        checkParticipatingOrThrow(query.missionaryId(), getUserIdByMemberId(memberId));

        return missionaryBoardService.getMissionaryBoards(query);
    }

    @Override
    public void updateBoard(@NonNull String memberId, @NonNull UpdateMissionaryBoardCommand command) throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();
    }

    @Override
    public void deleteMissionaryBoard(@NonNull String memberId, @NonNull DeleteMissionaryBoardCommand command) throws MissionaryBoardException {
        throw new AccessDeniedMissionaryBoardException();

    }


    private String getUserIdByMemberId(@NonNull String memberId) throws MemberException {
        return memberExternalService.getUserByMemberId(memberId).id();
    }

    private void checkParticipatingOrThrow(@NonNull String missionaryId, @NonNull String userId) throws AccessDeniedMissionaryBoardException {
        if (!participationService.isParticipating(missionaryId, userId)) {
            throw new AccessDeniedMissionaryBoardException();
        }
    }

}
