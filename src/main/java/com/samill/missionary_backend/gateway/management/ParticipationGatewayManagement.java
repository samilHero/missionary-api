package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.common.dto.UserContext;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.ParticipationGatewayMapper;
import com.samill.missionary_backend.gateway.dto.Participation.*;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.participation.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api")
public class ParticipationGatewayManagement {
    private final ParticipationExternalService participationExternalService;

    @GetMapping("/participations")
    public List<GetParticipationResult> getParticipations(GetParticipations getParticipation) {
        GetParticipationsQuery getParticipationsQuery
                = ParticipationGatewayMapper.INSTANCE.getParticipationsToGetParticipationsQuery(getParticipation);
        List<GetParticipationQueryResult> list = participationExternalService.getParticipations(getParticipationsQuery);
        return ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultsToGetParticipationResults(list);
    }

    @GetMapping("/participation/{participationId}")
    public GetParticipationResult getParticipation(@PathVariable String participationId, UserContext userContext) throws CommonException {
        GetParticipationQueryResult result = participationExternalService.getParticipation(participationId, userContext);
        return ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultToGetParticipationResult(result);
    }

    @PostMapping("/participation")
    public void createParticipation(CreateParticipation createParticipation, UserContext userContext) throws CommonException {
        createParticipation.setUserInfo(userContext);
        CreateParticipationCommand command = ParticipationGatewayMapper.INSTANCE.createParticipationToCreateParticipationCommand(createParticipation);
        participationExternalService.createParticipation(command);
    }

    @PutMapping("/participation")
    public void updateParticipation(UpdateParticipation updateParticipation) throws CommonException {
        UpdateParticipationCommand command = ParticipationGatewayMapper.INSTANCE.updateParticipationToUpdateParticipationCommand(updateParticipation);
        participationExternalService.updateParticipation(command);
    }

    @DeleteMapping("/participation")
    public void deleteParticipation(DeleteParticipation deleteParticipation) throws CommonException {
        DeleteParticipationCommand command = ParticipationGatewayMapper.INSTANCE.deleteParticipationToDeleteParticipationCommand(deleteParticipation);
        participationExternalService.deleteParticipation(command);
    }
}
