package com.samill.missionary_backend.team.service;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.team.dto.UpdateTeamCommand;
import com.samill.missionary_backend.team.entity.Team;
import com.samill.missionary_backend.team.entity.TeamMember;

import java.util.List;

public interface TeamService {
    void createTeam(Team team);
    void updateTeam(String teamId, UpdateTeamCommand team) throws CommonException;
    void deleteTeam(String teamId);
    Team getTeam(String teamId) throws CommonException;
    void updateTeamMember(String teamId, List<TeamMember> teamMembers) throws CommonException;
}
