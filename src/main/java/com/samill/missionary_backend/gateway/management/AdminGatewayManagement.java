package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.gateway.dto.CreateAdminRequest;
import com.samill.missionary_backend.gateway.dto.CreateChurchRequest;
import com.samill.missionary_backend.gateway.dto.CreateChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchesResult;
import com.samill.missionary_backend.gateway.dto.LoginAdminRequest;
import com.samill.missionary_backend.gateway.dto.LoginAdminResult;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipationResult;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipations;
import com.samill.missionary_backend.gateway.dto.UpdateChurchRequest;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.AdminGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.ChurchGatewayMapper;
import com.samill.missionary_backend.member.MemberManagement;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminGatewayManagement {


    private final MemberManagement memberManagement;
    private final ChurchExternalService churchExternalService;
    private final MissionaryExternalService missionaryExternalService;
    private final ParticipationExternalService participationExternalService;

    @PostMapping(AdminGatewayManagementEndPoint.CREATE_ADMIN_URI)
    public void signUp(@Valid @RequestBody CreateAdminRequest request) throws Exception {
        memberManagement.createAdmin(
            AdminGatewayMapper.INSTANCE.createAdminRequestToCreateAdminCommand(request)
        );
    }

    @PostMapping(AdminGatewayManagementEndPoint.ADMIN_LOGIN_URI)
    public LoginAdminResult login(@Valid @RequestBody LoginAdminRequest request) throws Exception {
        return AdminGatewayMapper.INSTANCE.loginAdminQueryResultToLoginAdminResult(
            memberManagement.loginAdmin(
                AdminGatewayMapper.INSTANCE.loginAdminRequestToLoginAdminQuery(request)
            )
        );
    }

    @GetMapping(AdminGatewayManagementEndPoint.GET_CHURCH)
    public GetChurchResult getChurch(@PathVariable String churchId) throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChruchQueryResultToGetChurchResult(churchExternalService.getChurch(churchId));
    }

    @GetMapping(AdminGatewayManagementEndPoint.GET_CHURCHES)
    public GetChurchesResult getChurches() throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChurchesQueryResultToGetChurchesResult(churchExternalService.getChurches());
    }

    @PostMapping(AdminGatewayManagementEndPoint.CREATE_CHURCH)
    public CreateChurchResult createChurch(@Valid @RequestBody CreateChurchRequest createChurchRequest) throws CommonException {
        return ChurchGatewayMapper.INSTANCE.createChurchCommandResultToCreateChurchResult(
            churchExternalService.createChurch(
                ChurchGatewayMapper.INSTANCE.createChurchRequestToCreateChurchCommand(createChurchRequest)
            )
        );
    }

    @DeleteMapping(AdminGatewayManagementEndPoint.DELETE_CHURCH)
    public void deleteChurch(@PathVariable String churchId) {
        churchExternalService.deleteChurch(churchId);
    }

    @PutMapping(AdminGatewayManagementEndPoint.UPDATE_CHURCH)
    public void updateChurch(@PathVariable String churchId, UpdateChurchRequest updateChurchRequest) throws CommonException {
        churchExternalService.updateChurch(churchId, ChurchGatewayMapper.INSTANCE.updateChurchRequestToUpdateChurchCommand(updateChurchRequest));
    }

    @GetMapping(AdminGatewayManagementEndPoint.GET_PARTICIPATIONS)
    public List<GetParticipationResult> getParticipations(GetParticipations getParticipation) {
        GetParticipationsQuery getParticipationsQuery
                = ParticipationGatewayMapper.INSTANCE.getParticipationsToGetParticipationsQuery(getParticipation);
        List<GetParticipationQueryResult> list = participationExternalService.getParticipations(getParticipationsQuery);
        return ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultsToGetParticipationResults(list);
    }

    @GetMapping(AdminGatewayManagementEndPoint.GET_PARTICIPATION)
    public GetParticipationResult getParticipation(@PathVariable String participationId) throws CommonException {
        GetParticipationQueryResult result = participationExternalService.getParticipation(participationId);
        return ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultToGetParticipationResult(result);
    }
}
