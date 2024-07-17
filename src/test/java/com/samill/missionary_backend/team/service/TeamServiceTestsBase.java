package com.samill.missionary_backend.team.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.samill.missionary_backend.MissionaryBackendApplication;
import com.samill.missionary_backend.common.AbstractControllerTestsBase;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.CreateTeamServiceCommand;
import com.samill.missionary_backend.missionary.dto.UpdateTeamServiceCommand;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQuery;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQueryResult;
import com.samill.missionary_backend.missionary.team.entity.Team;
import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import com.samill.missionary_backend.missionary.team.repository.TeamMemberRepository;
import com.samill.missionary_backend.missionary.team.repository.TeamRepository;
import com.samill.missionary_backend.missionary.team.service.TeamService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


@ContextConfiguration(classes = MissionaryBackendApplication.class)
class TeamServiceTestsBase extends AbstractControllerTestsBase {

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
        CreateTeamServiceCommand createTeamServiceCommand = CreateTeamServiceCommand.builder()
            .churchName("삼일교회")
            .churchId("CHURCH-1")
            .missionaryId("MISSIONARY-1")
            .leaderUserId("ych")
            .build();

        teamService.createTeam(createTeamServiceCommand);

        GetTeamListQuery getTeamListQuery = GetTeamListQuery.builder()
            .churchId(null)
            .leaderUserId(null)
            .build();

        List<GetTeamListQueryResult> list = teamService.getTeams(createTeamServiceCommand.missionaryId(), getTeamListQuery);
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("팀 수정")
    @Transactional
    void updateTeam() throws CommonException {
        CreateTeamServiceCommand createTeamServiceCommand = CreateTeamServiceCommand.builder()
            .churchName("삼일교회")
            .churchId("CHURCH-1")
            .missionaryId("MISSIONARY-1")
            .leaderUserId("ych")
            .build();

        Team savedTeam = teamService.createTeam(createTeamServiceCommand);

        UpdateTeamServiceCommand updateTeamInfo = UpdateTeamServiceCommand.builder()
            .churchName("강동교회")
            .churchId("CHURCH-1")
            .leaderUserId("ffjfj")
            .build();
        teamService.updateTeam(savedTeam.getId(), updateTeamInfo);
        assertEquals("강동교회", teamService.getTeam(savedTeam.getId()).getChurchName());
    }

    @Test
    @DisplayName("팀 정보 조회")
    @Transactional
    void getTeamMembers() throws CommonException {
        CreateTeamServiceCommand createTeamServiceCommand = CreateTeamServiceCommand.builder()
            .churchName("삼일교회")
            .churchId("CHURCH-1")
            .missionaryId("MISSIONARY-1")
            .leaderUserId("ych")
            .build();

        Team team = teamService.createTeam(createTeamServiceCommand);

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