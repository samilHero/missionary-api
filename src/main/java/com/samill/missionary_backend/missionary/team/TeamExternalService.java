package com.samill.missionary_backend.missionary.team;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.team.dto.*;

import java.util.List;

public interface TeamExternalService {
    void createTeam(CreateTeamCommand createTeamCommand);
    void updateTeam(String teamId, UpdateTeamCommand updateTeamCommand) throws CommonException;
    void updateTeamMember(String teamId, List<UpdateTeamMemberCommand> updateTeamMemberCommand) throws CommonException;
    void deleteTeam(String teamId);
    GetTeamQueryResult getTeam(String teamId) throws CommonException;
    List<GetTeamQueryResult> getTeams(String missionaryId);
}
