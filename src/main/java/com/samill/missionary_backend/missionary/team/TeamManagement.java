package com.samill.missionary_backend.missionary.team;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.*;
import com.samill.missionary_backend.missionary.team.entity.Team;
import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import com.samill.missionary_backend.missionary.team.mapper.TeamMapper;
import com.samill.missionary_backend.missionary.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class TeamManagement implements TeamExternalService {
    private final TeamService teamService;

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
}
