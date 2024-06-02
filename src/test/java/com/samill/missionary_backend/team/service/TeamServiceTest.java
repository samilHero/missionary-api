package com.samill.missionary_backend.team.service;

import com.samill.missionary_backend.MissionaryBackendApplication;
import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.UpdateTeamCommand;
import com.samill.missionary_backend.missionary.team.entity.Team;
import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import com.samill.missionary_backend.missionary.team.repository.TeamMemberRepository;
import com.samill.missionary_backend.missionary.team.repository.TeamRepository;
import com.samill.missionary_backend.missionary.team.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration(classes = MissionaryBackendApplication.class)
class TeamServiceTest extends AbstractControllerTest {
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Test
    @DisplayName("팀목록 조회")
    @Transactional
    void getTeams() {
        Team team = Team.builder()
                .teamName("삼일교회")
                .churchId("CHURCH-1")
                .missionaryId("MISSIONARY-1")
                .leaderUserId("ych")
                .build();

        teamService.createTeam(team);

        List<Team> list = teamService.getTeams("MISSIONARY-1");
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("팀 수정")
    @Transactional
    void updateTeam() throws CommonException {
        Team team = Team.builder()
                .teamName("삼일교회")
                .churchId("CHURCH-1")
                .missionaryId("MISSIONARY-1")
                .leaderUserId("ych")
                .build();
        teamRepository.save(team);
        UpdateTeamCommand command = UpdateTeamCommand.builder()
                .teamName("강동교회")
                .churchId("CHURCH-1")
                .leaderUserId("ffjfj")
                .build();
        teamService.updateTeam(team.getId(), command);
        assertEquals("강동교회", teamService.getTeam(team.getId()).getTeamName());
    }

    @Test
    @DisplayName("팀 정보 조회")
    @Transactional
    void getTeamMembers() throws CommonException {
        Team team = Team.builder()
                .teamName("삼일교회")
                .churchId("CHURCH-1")
                .missionaryId("MISSIONARY-1")
                .leaderUserId("ych")
                .teamMemberList(new ArrayList<TeamMember>())
                .build();
        teamService.createTeam(team);

        List<TeamMember> list = new ArrayList<>();

        TeamMember teamMember1 = TeamMember.builder()
                .userId("test1")
                .team(team)
                .build();

        TeamMember teamMember2 = TeamMember.builder()
                .userId("test2")
                .team(team)
                .build();
        list.add(teamMember1);
        list.add(teamMember2);

        teamService.updateTeamMember(team.getId(), list);
        assertEquals(2, teamService.getTeam(team.getId()).getTeamMemberList().size());
    }
}