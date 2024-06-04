package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardAdminModule;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardModule;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardModuleMapFactory;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardStaffModule;
import com.samill.missionary_backend.missionary.board.module.MissionaryBoardUserModule;
import com.samill.missionary_backend.missionary.board.service.MissionaryBoardService;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommandResult;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.CreateParticipationCommand;
import com.samill.missionary_backend.missionary.dto.CreateTeamCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.missionary.dto.DisappointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryIdsQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import com.samill.missionary_backend.missionary.dto.GetTeamQueryResult;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.UpdateParticipationCommand;
import com.samill.missionary_backend.missionary.dto.UpdateTeamCommand;
import com.samill.missionary_backend.missionary.dto.UpdateTeamMemberCommand;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.mapper.MissionaryBoardMapper;
import com.samill.missionary_backend.missionary.missionary.mapper.MissionaryMapper;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import com.samill.missionary_backend.missionary.participation.entity.Participation;
import com.samill.missionary_backend.missionary.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.missionary.participation.service.ParticipationService;
import com.samill.missionary_backend.missionary.staff.service.MissionaryStaffService;
import com.samill.missionary_backend.missionary.team.entity.Team;
import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import com.samill.missionary_backend.missionary.team.mapper.TeamMapper;
import com.samill.missionary_backend.missionary.team.service.TeamService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ParticipationService participationService;
    private final TeamService teamService;

    @Override
    @Transactional
    public @NonNull CreateMissionaryCommandResult createMissionary(@NonNull CreateMissionaryCommand createMissionaryCommand)
        throws MissionaryException {
        return missionaryService.createMissionary(createMissionaryCommand);
    }

    @Override
    @Transactional
    public void updateMissionary(@NonNull String missionaryId, @NonNull UpdateMissionaryCommand updateMissionaryCommand) throws MissionaryException {
        missionaryService.updateMissionary(missionaryId, updateMissionaryCommand);
    }

    @Override
    public void deleteMissionary(@NonNull String missionaryId) {
        missionaryService.deleteMissionary(missionaryId);
    }

    @Override
    public @NonNull GetMissionaryQueryResult getMissionary(@NonNull GetMissionaryQuery getMissionaryQuery) throws MissionaryException {
        return MissionaryMapper.INSTANCE.missionaryToGetMissionaryQueryResult(missionaryService.getMissionary(getMissionaryQuery.missionaryId()));
    }

    @Override
    public void getMissionaries(String cursor) {
        missionaryService.getMissionaries(cursor);
    }


    @Override
    public boolean isInParticipationPeriod(@NonNull String missionaryId) throws MissionaryException {
        return missionaryService.isParticipationPeriod(missionaryId);
    }

    @Override
    public @NonNull List<String> getDaysBeforeMissionaryIds(GetMissionaryIdsQuery getMissionaryIdsQuery) {
        return null;
    }

    @Override
    public @NonNull CreateMissionaryBoardCommandResult createMissionaryBoard(
        @NonNull String memberId,
        @NonNull CreateMissionaryBoardCommand command
    ) throws CommonException {
        return new CreateMissionaryBoardCommandResult(
            getMissionaryBoardModule(memberId, command.missionaryId()).createMissionaryBoard(memberId, command)
        );
    }


    @Override
    public @NonNull GetMissionaryBoardsQueryResult getMissionaryBoards(
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

    @Override
    public void appointStaffs(@NonNull AppointMissionaryStaffsCommand command) throws MissionaryException {

    }

    @Override
    public void disappointStaffs(@NonNull DisappointMissionaryStaffsCommand command) throws MissionaryException {

    }

    MissionaryBoardModule getMissionaryBoardModule(
        @NonNull
        String memberId,
        @NonNull
        String missionaryId
    ) throws CommonException {

        final var getMemberServiceTypeDto = memberExternalService.getMemberServiceType(memberId);

        final var missionaryBoardAdminModuleClass = switch (getMemberServiceTypeDto.serviceType()) {
            case ADMIN_SERVICE -> MissionaryBoardAdminModule.class;
            case USER_SERVICE -> missionaryStaffService.isExistedMissionaryStaff(
                missionaryId,
                memberExternalService.getUserByMemberId(memberId).id()
            ) ? MissionaryBoardStaffModule.class : MissionaryBoardUserModule.class;
        };

        return missionaryBoardModuleMapFactory.getMissionaryBoardModule(missionaryBoardAdminModuleClass);
    }

    @Override
    public void createTeam(CreateTeamCommand createTeamCommand) {
        Team team = TeamMapper.INSTANCE.createTeamCommandToEntity(createTeamCommand);
        teamService.createTeam(team);
    }

    @Override
    public void updateTeam(String teamId, UpdateTeamCommand updateTeamCommand) throws CommonException {
        teamService.updateTeam(teamId, updateTeamCommand);
    }

    @Override
    public void updateTeamMember(String teamId, List<UpdateTeamMemberCommand> updateTeamMemberCommand) throws CommonException {
        List<TeamMember> teamMembers = TeamMapper.INSTANCE.updateTeamMemberCommandToEntity(updateTeamMemberCommand);
        teamService.updateTeamMember(teamId, teamMembers);
    }

    @Override
    public void deleteTeam(String teamId) {
        teamService.deleteTeam(teamId);
    }

    @Override
    public GetTeamQueryResult getTeam(String teamId) throws CommonException {
        Team team = teamService.getTeam(teamId);
        return TeamMapper.INSTANCE.entityToGetTeamQueryResult(team);
    }

    @Override
    public List<GetTeamQueryResult> getTeams(String missionaryId) {
        List<Team> teams = teamService.getTeams(missionaryId);
        return TeamMapper.INSTANCE.entityToGetTeamsQueryResult(teams);
    }

    @Override
    public void createParticipation(@NonNull CreateParticipationCommand createParticipationCommand) throws Exception {
        validateParticipationPeriod(createParticipationCommand.getMissionaryId());
        int maxUserCount = getMissionaryMaxCount(createParticipationCommand.getMissionaryId());
        GetUserDto user = memberExternalService.getUserById(createParticipationCommand.getUserId());
        updateCommandWithFeeAndUserInfo(createParticipationCommand, user);
        participationService.createParticipation(createParticipationCommand, maxUserCount);
    }


    @Override
    public void deleteParticipation(String participationId, @NonNull DeleteParticipationCommand deleteParticipationCommand) throws CommonException {
        participationService.deleteParticipation(participationId, deleteParticipationCommand);
    }

    @Override
    public Page<GetParticipationQueryResult> getParticipations(String missionaryId, GetParticipationsQuery getParticipationsQuery,
        Pageable pageable) {
        return participationService.getParticipations(missionaryId, getParticipationsQuery, pageable);
    }

    @Override
    public GetParticipationQueryResult getParticipation(String participationId) throws CommonException {
        //user 체크 필요
        Participation participation = participationService.getParticipation(participationId);
        return ParticipationMapper.INSTANCE.entityToGetParticipationQueryResult(participation);
    }


    @Override
    public void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException {
        participationService.updateParticipation(participationId, updateParticipationCommand);
    }

    @Override
    public void updateParticipationPaid(List<String> ids) {
        participationService.updateParticipationPaid(ids);
    }

    private void validateParticipationPeriod(String missionaryId) throws CommonException {
        if (!missionaryService.isParticipationPeriod(missionaryId)) {
            throw new CommonException(ResponseCode.INVALID_PARTICIPATION_PERIOD);
        }
    }

    private void updateCommandWithFeeAndUserInfo(CreateParticipationCommand createParticipationCommand, GetUserDto user) throws Exception {
        createParticipationCommand.setApplyFee(getApplyFee(createParticipationCommand.getMissionaryId()));
        createParticipationCommand.updateUserInfo(user);
    }

    private int getMissionaryMaxCount(String missionaryId) throws CommonException {
        return missionaryService.getMissionary(missionaryId).getDetail().getMaximumParticipantCount();
    }

    private int getApplyFee(String missionaryId) throws CommonException {
        return missionaryService.getMissionary(missionaryId).getDetail().getPrice();
    }
}
