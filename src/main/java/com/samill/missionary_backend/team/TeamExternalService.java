package com.samill.missionary_backend.team;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.team.dto.CreateTeamCommand;
import com.samill.missionary_backend.team.dto.UpdateTeamCommand;
import com.samill.missionary_backend.team.dto.UpdateTeamMemberCommand;

import java.util.List;

public interface TeamExternalService {
    void createTeam(CreateTeamCommand createTeamCommand);
    void updateTeam(String teamId, UpdateTeamCommand updateTeamCommand) throws CommonException;
    void updateTeamMember(String teamId, List<UpdateTeamMemberCommand> updateTeamMemberCommand) throws CommonException;
    void deleteTeam(String teamId);
}
