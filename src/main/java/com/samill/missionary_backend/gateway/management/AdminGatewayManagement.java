package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.*;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipationResult;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.AdminGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.ChurchGatewayMapper;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminGatewayManagement {


    private final MemberExternalService memberExternalService;
    private final ChurchExternalService churchExternalService;
    private final MissionaryExternalService missionaryExternalService;

    @PostMapping(AdminGatewayManagementEndPoint.CREATE_ADMIN_URI)
    public void signUp(@Valid @RequestBody CreateAdminRequest request) throws Exception {
        memberExternalService.createAdmin(
            AdminGatewayMapper.INSTANCE.createAdminRequestToCreateAdminCommand(request)
        );
    }

    @PostMapping(AdminGatewayManagementEndPoint.ADMIN_LOGIN_URI)
    public LoginAdminResult login(@Valid @RequestBody LoginAdminRequest request) throws Exception {
        return AdminGatewayMapper.INSTANCE.loginAdminQueryResultToLoginAdminResult(
            memberExternalService.loginAdmin(
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
    public Page<GetParticipationResult> getParticipationList(@PathVariable String missionaryId, GetParticipationsRequest getParticipationsRequest, Pageable pageable) {
        GetParticipationsQuery getParticipationsQuery
                = ParticipationGatewayMapper.INSTANCE.getParticipationsToGetParticipationsQuery(getParticipationsRequest);
        Page<GetParticipationQueryResult> list = missionaryExternalService.getParticipations(missionaryId, getParticipationsQuery, pageable);
        return list.map(ParticipationGatewayMapper.INSTANCE::getParticipationQueryResultToGetParticipationResult);
    }

    @GetMapping(AdminGatewayManagementEndPoint.GET_PARTICIPATION)
    public GetParticipationResult getParticipation(@PathVariable String participationId) throws CommonException {
        GetParticipationQueryResult result = missionaryExternalService.getParticipation(participationId);
        return ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultToGetParticipationResult(result);
    }
}
