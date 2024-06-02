package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.*;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipationResult;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipationsResult;
import com.samill.missionary_backend.gateway.endPoint.StaffGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.ChurchGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.TeamGatewayMapper;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StaffGatewayManagement {

    private final ChurchExternalService churchExternalService;
    private final ParticipationExternalService participationExternalService;
    private final MissionaryExternalService missionaryExternalService;

    @GetMapping(StaffGatewayManagementEndPoint.GET_CHURCH)
    public GetChurchResult getChurch(@PathVariable String churchId) throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChruchQueryResultToGetChurchResult(churchExternalService.getChurch(churchId));
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_CHURCHES)
    public GetChurchesResult getChurches() throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChurchesQueryResultToGetChurchesResult(churchExternalService.getChurches());
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_PARTICIPATIONS)
    public Page<GetParticipationResult> getParticipations(GetParticipationsResult getParticipation, Pageable pageable) {
        GetParticipationsQuery getParticipationsQuery
                = ParticipationGatewayMapper.INSTANCE.getParticipationsToGetParticipationsQuery(getParticipation);
        Page<GetParticipationQueryResult> list = participationExternalService.getParticipations(getParticipationsQuery, pageable);
        return list.map(ParticipationGatewayMapper.INSTANCE::getParticipationQueryResultToGetParticipationResult);
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_PARTICIPATION)
    public GetParticipationResult getParticipation(@PathVariable String participationId) throws CommonException {
        GetParticipationQueryResult result = participationExternalService.getParticipation(participationId);
        return ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultToGetParticipationResult(result);
    }

    @PostMapping(StaffGatewayManagementEndPoint.CREATE_TEAM)
    public void createTeam(@Valid @RequestBody CreateTeamRequest createTeamRequest) {
        missionaryExternalService.createTeam(TeamGatewayMapper.INSTANCE.createTeamRequestToCreateTeamCommand(createTeamRequest));
    }

    @PutMapping(StaffGatewayManagementEndPoint.UPDATE_TEAM)
    public void updateTeam(@PathVariable String teamId, @RequestBody UpdateTeamRequest updateTeamRequest) throws CommonException {
        missionaryExternalService.updateTeam(teamId, TeamGatewayMapper.INSTANCE.updateTeamRequestToUpdateTeamCommand(updateTeamRequest));
    }

    @PutMapping(StaffGatewayManagementEndPoint.UPDATE_TEAM_MEMBER)
    public void updateTeamMembers(@PathVariable String teamId, @Valid @RequestBody List<UpdateTeamMemberRequest> list) throws CommonException {
        missionaryExternalService.updateTeamMember(teamId, TeamGatewayMapper.INSTANCE.updateTeamMEmberRequestsToUpdateTeamMEmberCommands(list));
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_TEAM)
    public GetTeamResult getTeam(@PathVariable String teamId) throws CommonException {
        return TeamGatewayMapper.INSTANCE.GetTeamQueryResultToGetTeamResult(missionaryExternalService.getTeam(teamId));
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_TEAMS)
    public List<GetTeamResult> getTeams(@PathVariable String missionaryId) {
        return TeamGatewayMapper.INSTANCE.GetTeamQueryResultsToGetTeamResults(missionaryExternalService.getTeams(missionaryId));
    }

    @DeleteMapping(StaffGatewayManagementEndPoint.DELETE_TEAM)
    public void deleteTeam(@PathVariable String teamId) {
        missionaryExternalService.deleteTeam(teamId);
    }
}
