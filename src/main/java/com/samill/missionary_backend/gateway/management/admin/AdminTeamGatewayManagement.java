package com.samill.missionary_backend.gateway.management.admin;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.CreateTeamRequest;
import com.samill.missionary_backend.gateway.dto.GetTeamListRequest;
import com.samill.missionary_backend.gateway.dto.GetTeamResult;
import com.samill.missionary_backend.gateway.dto.GetTeamResults;
import com.samill.missionary_backend.gateway.dto.UpdateTeamMemberRequest;
import com.samill.missionary_backend.gateway.dto.UpdateTeamRequest;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.TeamGatewayMapper;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQueryResult;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminTeamGatewayManagement {

    private final MissionaryExternalService missionaryExternalService;

    // 팀장 및 팀 생성
    @PostMapping(AdminGatewayManagementEndPoint.CREATE_TEAM)
    public void createTeam(@Valid @RequestBody CreateTeamRequest createTeamRequest) throws CommonException {
        missionaryExternalService.createTeam(TeamGatewayMapper.INSTANCE.createTeamRequestToCreateTeamCommand(createTeamRequest));
    }

    // 팀장 및 팀 연계교회 수정
    @PutMapping(AdminGatewayManagementEndPoint.UPDATE_TEAM)
    public void updateTeam(@PathVariable String teamId, @RequestBody UpdateTeamRequest updateTeamRequest) throws CommonException {
        missionaryExternalService.updateTeam(teamId, TeamGatewayMapper.INSTANCE.updateTeamRequestToUpdateTeamCommand(updateTeamRequest));
    }

    // 팀 멤버 매칭
    @PutMapping(AdminGatewayManagementEndPoint.UPDATE_TEAM_MEMBER)
    public void updateTeamMembers(@PathVariable String teamId, @Valid @RequestBody List<UpdateTeamMemberRequest> list) throws CommonException {
        missionaryExternalService.updateTeamMember(teamId, TeamGatewayMapper.INSTANCE.updateTeamMEmberRequestsToUpdateTeamMEmberCommands(list));
    }

    // 필요할지 모르지만 팀 조회
    @GetMapping(AdminGatewayManagementEndPoint.GET_TEAM)
    public GetTeamResult getTeam(@PathVariable String teamId) throws CommonException {
        return TeamGatewayMapper.INSTANCE.GetTeamQueryResultToGetTeamResult(missionaryExternalService.getTeam(teamId));
    }

    // 팀 현황
    @GetMapping(AdminGatewayManagementEndPoint.GET_TEAMS)
    public List<GetTeamResults> getTeams(@PathVariable String missionaryId, GetTeamListRequest getTeamListRequest) {
        List<GetTeamListQueryResult> list = missionaryExternalService.getTeams(missionaryId,
            TeamGatewayMapper.INSTANCE.getTeamListRequestToGetTeamListQuery(getTeamListRequest));
        return TeamGatewayMapper.INSTANCE.GetTeamListQueryResultsToGetTeamResults(list);
    }

    // 필요할지 모르지만 팀 삭제
    @DeleteMapping(AdminGatewayManagementEndPoint.DELETE_TEAM)
    public void deleteTeam(@PathVariable String teamId) {
        missionaryExternalService.deleteTeam(teamId);
    }
}
