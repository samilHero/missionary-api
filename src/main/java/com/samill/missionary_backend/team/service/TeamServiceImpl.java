package com.samill.missionary_backend.team.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.team.dto.UpdateTeamCommand;
import com.samill.missionary_backend.team.entity.Team;
import com.samill.missionary_backend.team.entity.TeamMember;
import com.samill.missionary_backend.team.repository.TeamMemberRepository;
import com.samill.missionary_backend.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void updateTeam(String teamId, UpdateTeamCommand updateTeam) throws CommonException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CommonException(ResponseCode.TEAM_NOT_FOUND));
        team.updateInfo(updateTeam);
    }

    @Override
    public void deleteTeam(String teamId) {
        teamRepository.deleteById(teamId);
    }

    @Override
    public Team getTeam(String teamId) throws CommonException {
        return teamRepository.findById(teamId).orElseThrow(() -> new CommonException(ResponseCode.TEAM_NOT_FOUND));
    }

    @Override
    public void updateTeamMember(String teamId, List<TeamMember> teamMembers) throws CommonException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CommonException(ResponseCode.TEAM_NOT_FOUND));

        teamMembers.stream()
                .forEach(teamMember -> {
                    teamMemberRepository.deleteByMissionaryIdAndUserId(team.getMissionaryId(), teamMember.getUserId());
                    team.addTeamMember(teamMember);
                });
    }
}
