package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.*;

import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionaryExternalService {

    CreateMissionaryCommandResult createMissionary(
        @NonNull
        CreateMissionaryCommand createMissionaryCommand
    ) throws CommonException;

    void updateMissionary(
        @NonNull
        String missionaryId,
        @NonNull
        UpdateMissionaryCommand updateMissionaryCommand
    ) throws CommonException;

    void deleteMissionary(@NonNull String missionaryId);

    GetMissionaryQueryResult getMissionary(@NonNull GetMissionaryQuery getMissionaryQuery) throws CommonException;

    void getMissionaries(String cursor);


    boolean isInParticipationPeriod(@NonNull String missionaryId) throws CommonException;

    List<String> getDaysBeforeMissionaryIds(GetMissionaryIdsQuery getMissionaryIdsQuery);

    CreateMissionaryBoardCommandResult createMissionaryBoard(
        @NonNull String memberId,
        @NonNull CreateMissionaryBoardCommand command
    ) throws CommonException;


    GetMissionaryBoardsQueryResult getMissionaryBoards(
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

    //    void writeMissionaryBoard(
    //        @NonNull String memberId,
    //        @NonNull WriteMissionaryBoardDto writeMissionaryBoardDto
    //    );
    //
    //    MissionaryBoardDto getMissionaryBoard(
    //        @NonNull String memberId,
    //        @NonNull String missionaryBoardId
    //    );
    //
    //    void getMissionaryBoards(
    //        @NonNull String memberId,
    //        @NonNull String missionaryBoardId,
    //        String cursor
    //    );

    //    void updateMissionaryBoard(
    //        @NonNull String memberId,
    //        @NonNull String missionaryBoardId,
    //        @NonNull UpdateMissionaryBoardDto updateMissionaryBoardDto
    //    );
    //
    //    void deleteMissionaryBoard(
    //        @NonNull String memberId,
    //        @NonNull String missionaryBoardId
    //    );
    //
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
}
