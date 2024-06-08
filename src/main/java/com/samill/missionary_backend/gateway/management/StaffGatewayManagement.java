package com.samill.missionary_backend.gateway.management;

import com.opencsv.CSVWriter;
import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.CreateTeamRequest;
import com.samill.missionary_backend.gateway.dto.GetChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchesResult;
import com.samill.missionary_backend.gateway.dto.GetParticipationsDownloadRequest;
import com.samill.missionary_backend.gateway.dto.GetParticipationsRequest;
import com.samill.missionary_backend.gateway.dto.GetTeamResult;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipationResult;
import com.samill.missionary_backend.gateway.dto.UpdateTeamMemberRequest;
import com.samill.missionary_backend.gateway.dto.UpdateTeamRequest;
import com.samill.missionary_backend.gateway.endPoint.StaffGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.ChurchGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.TeamGatewayMapper;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class StaffGatewayManagement {

    private final ChurchExternalService churchExternalService;
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
    public Page<GetParticipationResult> getParticipationList(
        @PathVariable String missionaryId,
        GetParticipationsRequest getParticipationsRequest,
        Pageable pageable) {
        GetParticipationsQuery getParticipationsQuery
            = ParticipationGatewayMapper.INSTANCE.getParticipationsToGetParticipationsQuery(getParticipationsRequest);
        Page<GetParticipationQueryResult> list = missionaryExternalService.getParticipations(missionaryId, getParticipationsQuery, pageable);
        return list.map(ParticipationGatewayMapper.INSTANCE::getParticipationQueryResultToGetParticipationResult);
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_PARTICIPATION)
    public GetParticipationResult getParticipation(@PathVariable String participationId) throws CommonException {
        GetParticipationQueryResult result = missionaryExternalService.getParticipation(participationId);
        return ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultToGetParticipationResult(result);
    }

    @PutMapping(StaffGatewayManagementEndPoint.UPDATE_PARTICIPATION_APPROVE)
    public void updateParticipationPaid(List<String> ids) {
        missionaryExternalService.updateParticipationPaid(ids);
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_DOWNLOAD_PARTICIPATION_LIST)
    public void downloadParticipationListCsv(@PathVariable String missionaryId,
        GetParticipationsDownloadRequest getParticipationsDownloadRequest, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv; charset=UTF-8");
        String fileName = URLEncoder.encode("참가신청목록", "UTF-8");
        response.setHeader("Content-Disposition",
            "attachment; filename=\"" + fileName + "\"");

        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(),
            StandardCharsets.UTF_8);
        writer.write("\uFEFF");
        CSVWriter csvWriter = new CSVWriter(writer);

        List<String[]> list = missionaryExternalService.downloadParticipationListCsv(missionaryId,
            ParticipationGatewayMapper.INSTANCE.getParticipationsDownloadReqeustToGetParticipationDownloadQuery(getParticipationsDownloadRequest));

        csvWriter.writeAll(list);
        csvWriter.close();

        writer.close();
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
