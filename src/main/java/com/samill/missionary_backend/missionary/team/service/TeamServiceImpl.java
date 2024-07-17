package com.samill.missionary_backend.missionary.team.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.CreateTeamCommand;
import com.samill.missionary_backend.missionary.dto.CreateTeamServiceCommand;
import com.samill.missionary_backend.missionary.dto.UpdateTeamServiceCommand;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQuery;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQueryResult;
import com.samill.missionary_backend.missionary.team.mapper.TeamMapper;
import com.samill.missionary_backend.missionary.team.repository.TeamMemberRepository;
import com.samill.missionary_backend.missionary.dto.UpdateTeamCommand;
import com.samill.missionary_backend.missionary.team.entity.Team;
import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import com.samill.missionary_backend.missionary.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    public Team createTeam(CreateTeamServiceCommand createTeamServiceCommand) {
        Team team = TeamMapper.INSTANCE.createTeamServiceCommandToEntity(createTeamServiceCommand);
        return teamRepository.save(team);
    }

    @Override
    public void updateTeam(String teamId, UpdateTeamServiceCommand updateTeamServiceCommand) throws CommonException {
        Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new CommonException(ResponseCode.TEAM_NOT_FOUND));
        team.updateInfo(updateTeamServiceCommand);
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
    public List<GetTeamListQueryResult> getTeams(String missionaryId, GetTeamListQuery getTeamListQuery) {
        return teamRepository.findAllByQuery(missionaryId, getTeamListQuery);
    }

    @Override
    public void updateTeamMember(String teamId, List<TeamMember> teamMembers) throws CommonException {
        Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new CommonException(ResponseCode.TEAM_NOT_FOUND));

        // 해당 선교의 팀 id list
        List<String> sameMissionaryIds = teamRepository.findByMissionaryId(team.getMissionaryId()).stream()
            .map(Team::getId).collect(Collectors.toList());

        teamMembers.stream()
            .forEach(teamMember -> {
                // 같은 선교의 팀에 소속되어 있다면 초기화
                teamMemberRepository.deleteByIdInAndUserId(sameMissionaryIds, teamMember.getUserId());
                team.addTeamMember(teamMember);
            });
    }
}
