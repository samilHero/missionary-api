package com.samill.missionary_backend.missionary.team.service;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.CreateTeamServiceCommand;
import com.samill.missionary_backend.missionary.dto.UpdateTeamCommand;
import com.samill.missionary_backend.missionary.dto.UpdateTeamServiceCommand;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQuery;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQueryResult;
import com.samill.missionary_backend.missionary.team.entity.Team;
import com.samill.missionary_backend.missionary.team.entity.TeamMember;

import java.util.List;

public interface TeamService {

    Team createTeam(CreateTeamServiceCommand team);

    void updateTeam(String teamId, UpdateTeamServiceCommand team) throws CommonException;

    void deleteTeam(String teamId);

    Team getTeam(String teamId) throws CommonException;

    List<GetTeamListQueryResult> getTeams(String missionaryId, GetTeamListQuery getTeamListQuery);

    void updateTeamMember(String teamId, List<TeamMember> teamMembers) throws CommonException;
}
