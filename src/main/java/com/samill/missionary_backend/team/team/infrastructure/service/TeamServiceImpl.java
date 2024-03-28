package com.samill.missionary_backend.team.team.infrastructure.service;


import com.samill.missionary_backend.team.team.domain.mapper.TeamMapper;
import com.samill.missionary_backend.team.team.domain.model.Team;
import com.samill.missionary_backend.team.team.domain.repository.TeamRepository;
import com.samill.missionary_backend.team.team.domain.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;


    @Override
    public Team getTeamById(String teamId) {
        return teamMapper.teamEntityToTeam(teamRepository.findById(teamId).orElseThrow());
    }

    @Override
    public List<Team> getTeamsByMissionaryId(String missionaryId) {
        return teamRepository.findAllByMissionaryId(missionaryId).stream()
                .map(teamMapper::teamEntityToTeam)
                .toList();
    }

    @Override
    public String getMissionaryIdByTeamId(String teamId) {
        return this.teamRepository.findById(teamId).orElseThrow().getMissionaryId();
    }

    @Override
    public boolean isExistTeam(String teamId) {
        return this.teamRepository.findById(teamId).isPresent();
    }
}
