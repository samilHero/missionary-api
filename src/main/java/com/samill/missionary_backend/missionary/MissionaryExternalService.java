package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.common.exception.CommonException;
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
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionaryExternalService {


    @NonNull CreateMissionaryCommandResult createMissionary(
        @NonNull
        CreateMissionaryCommand createMissionaryCommand
    ) throws MissionaryException;

    void updateMissionary(
        @NonNull
        String missionaryId,
        @NonNull
        UpdateMissionaryCommand updateMissionaryCommand
    ) throws MissionaryException;

    void deleteMissionary(@NonNull String missionaryId) throws MissionaryException;

    @NonNull GetMissionaryQueryResult getMissionary(@NonNull GetMissionaryQuery getMissionaryQuery) throws MissionaryException;

    void getMissionaries(String cursor);

    boolean isInParticipationPeriod(@NonNull String missionaryId) throws MissionaryException;

    @NonNull List<String> getDaysBeforeMissionaryIds(GetMissionaryIdsQuery getMissionaryIdsQuery);

    @NonNull CreateMissionaryBoardCommandResult createMissionaryBoard(
        @NonNull String memberId,
        @NonNull CreateMissionaryBoardCommand command
    ) throws CommonException;

    @NonNull GetMissionaryBoardsQueryResult getMissionaryBoards(
        @NonNull String memberId,
        @NonNull GetMissionaryBoardsQuery query
    ) throws CommonException;

    void updateMissionaryBoard(
        @NonNull
        String memberId,
        @NonNull
        UpdateMissionaryBoardCommand command
    ) throws CommonException;

    void deleteMissionaryBoard(
        @NonNull
        String memberId,
        @NonNull
        DeleteMissionaryBoardCommand command
    ) throws CommonException;

    void appointStaffs(@NonNull AppointMissionaryStaffsCommand command) throws MissionaryException;

    void disappointStaffs(@NonNull DisappointMissionaryStaffsCommand command) throws MissionaryException;

    void createTeam(CreateTeamCommand createTeamCommand);

    void updateTeam(String teamId, UpdateTeamCommand updateTeamCommand) throws CommonException;

    void updateTeamMember(String teamId, List<UpdateTeamMemberCommand> updateTeamMemberCommand) throws CommonException;

    void deleteTeam(String teamId);

    GetTeamQueryResult getTeam(String teamId) throws CommonException;

    List<GetTeamQueryResult> getTeams(String missionaryId);

    void createParticipation(CreateParticipationCommand createParticipationDto) throws Exception;

    void deleteParticipation(String participationId, DeleteParticipationCommand deleteParticipationCommand) throws CommonException;

    Page<GetParticipationQueryResult> getParticipations(String missionaryId, GetParticipationsQuery getParticipationsQuery, Pageable pageable);

    GetParticipationQueryResult getParticipation(String participationId) throws CommonException;

    void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException;

    void updateParticipationPaid(List<String> ids);
}
