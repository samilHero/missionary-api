package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.GetChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchesResult;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipationResult;
import com.samill.missionary_backend.gateway.dto.Participation.GetParticipations;
import com.samill.missionary_backend.gateway.endPoint.StaffGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.ChurchGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.missionary.participation.ParticipationExternalService;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class StaffGatewayManagement {

    private final ChurchExternalService churchExternalService;
    private final ParticipationExternalService participationExternalService;

    @GetMapping(StaffGatewayManagementEndPoint.GET_CHURCH)
    public GetChurchResult getChurch(@PathVariable String churchId) throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChruchQueryResultToGetChurchResult(churchExternalService.getChurch(churchId));
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_CHURCHES)
    public GetChurchesResult getChurches() throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChurchesQueryResultToGetChurchesResult(churchExternalService.getChurches());
    }

    @GetMapping(StaffGatewayManagementEndPoint.GET_PARTICIPATIONS)
    public Page<GetParticipationResult> getParticipations(GetParticipations getParticipation, Pageable pageable) {
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

}
