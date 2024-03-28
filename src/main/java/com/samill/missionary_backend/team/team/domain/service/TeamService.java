package com.samill.missionary_backend.team.team.domain.service;

import com.samill.missionary_backend.team.team.domain.model.Team;
import java.util.List;

public interface TeamService {

    Team getTeamById(String teamId);

    List<Team> getTeamsByMissionaryId(String missionaryId);

    String getMissionaryIdByTeamId(String teamId);


    boolean isExistTeam(String teamId);

}
