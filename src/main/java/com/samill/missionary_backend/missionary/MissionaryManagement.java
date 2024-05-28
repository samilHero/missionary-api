package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.member.dto.GetMemberServiceTypeDto;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardAdminModule;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardModule;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardModuleMapFactory;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardStaffModule;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardUserModule;
import com.samill.missionary_backend.missionary.board.service.MissionaryBoardService;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommandResult;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryIdsQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryCommand;
import com.samill.missionary_backend.missionary.mapper.MissionaryBoardMapper;
import com.samill.missionary_backend.missionary.missionary.mapper.MissionaryMapper;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import com.samill.missionary_backend.missionary.staff.service.MissionaryStaffService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class MissionaryManagement implements MissionaryExternalService {

    private final MissionaryService missionaryService;
    private final MissionaryStaffService missionaryStaffService;
    private final MemberExternalService memberExternalService;
    private final MissionaryBoardService missionaryBoardService;
    private final MissionaryBoardModuleMapFactory missionaryBoardModuleMapFactory;

    @Override
    @Transactional
    public CreateMissionaryCommandResult createMissionary(@NonNull CreateMissionaryCommand createMissionaryCommand) throws CommonException {
        return missionaryService.createMissionary(createMissionaryCommand);
    }

    @Override
    @Transactional
    public void updateMissionary(@NonNull String missionaryId, @NonNull UpdateMissionaryCommand updateMissionaryCommand) throws CommonException {
        missionaryService.updateMissionary(missionaryId, updateMissionaryCommand);
    }

    @Override
    public void deleteMissionary(@NonNull String missionaryId) {
        missionaryService.deleteMissionary(missionaryId);
    }

    @Override
    public GetMissionaryQueryResult getMissionary(@NonNull GetMissionaryQuery getMissionaryQuery) throws CommonException {
        return MissionaryMapper.INSTANCE.missionaryToGetMissionaryQueryResult(missionaryService.getMissionary(getMissionaryQuery.missionaryId()));
    }

    @Override
    public void getMissionaries(String cursor) {
        missionaryService.getMissionaries(cursor);
    }


    @Override
    public boolean isInParticipationPeriod(@NonNull String missionaryId) throws CommonException {
        return missionaryService.isParticipationPeriod(missionaryId);
    }

    @Override
    public List<String> getDaysBeforeMissionaryIds(GetMissionaryIdsQuery getMissionaryIdsQuery) {
        return null;
    }

    @Override
    public CreateMissionaryBoardCommandResult createMissionaryBoard(
        @NonNull String memberId,
        @NonNull CreateMissionaryBoardCommand command
    ) throws CommonException {
        return new CreateMissionaryBoardCommandResult(
            getMissionaryBoardModule(memberId, command.missionaryId()).createMissionaryBoard(memberId, command)
        );
    }


    @Override
    public GetMissionaryBoardsQueryResult getMissionaryBoards(
        @NonNull final String memberId,
        @NonNull final GetMissionaryBoardsQuery query
    ) throws CommonException {

        return MissionaryBoardMapper.INSTANCE.missionaryBoardsToGetMissionaryBoardsQueryResult(
            getMissionaryBoardModule(memberId, query.missionaryId()).getMissionaryBoards(memberId, query)
        );
    }


    @Override
    public void updateMissionaryBoard(
        @NonNull String memberId,
        @NonNull UpdateMissionaryBoardCommand command
    ) throws CommonException {
        final String missionaryId = missionaryBoardService.getMissionaryBoard(command.id()).getMissionaryId();
        getMissionaryBoardModule(memberId, missionaryId).updateBoard(memberId, command);
    }

    @Override
    public void deleteMissionaryBoard(
        @NonNull
        String memberId,
        @NonNull DeleteMissionaryBoardCommand command
    ) throws CommonException {
        final String missionaryId = missionaryBoardService.getMissionaryBoard(command.missionaryBoardId()).getMissionaryId();
        getMissionaryBoardModule(memberId, missionaryId).deleteMissionaryBoard(memberId, command);
    }

    MissionaryBoardModule getMissionaryBoardModule(
        @NonNull
        String memberId,
        @NonNull
        String missionaryId
    ) throws CommonException {
        final GetMemberServiceTypeDto getMemberServiceTypeDto = memberExternalService.getMemberServiceType(memberId);

        final var missionaryBoardAdminModuleClass = switch (getMemberServiceTypeDto.serviceType()) {
            case ADMIN_SERVICE -> MissionaryBoardAdminModule.class;
            case USER_SERVICE -> missionaryStaffService.isExistedMissionaryStaff(
                missionaryId,
                memberExternalService.getUserByMemberId(memberId).id()
            ) ? MissionaryBoardStaffModule.class : MissionaryBoardUserModule.class;
        };

        return missionaryBoardModuleMapFactory.getMissionaryBoardModule(missionaryBoardAdminModuleClass);
    }


}
