package com.samill.application.team;


public interface TeamService {
    Team createTeam(Team team, Long missionaryId);
    Team updateTeam(Team team);
}
