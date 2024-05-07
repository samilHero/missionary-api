package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.CreateChurchRequest;
import com.samill.missionary_backend.gateway.dto.CreateChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchesResult;
import com.samill.missionary_backend.gateway.dto.UpdateChurchRequest;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndpoint;
import com.samill.missionary_backend.gateway.mapper.ChurchGatewayMapper;
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
public class AdminGatewayManagement {


    private final ChurchExternalService churchExternalService;

    @GetMapping(AdminGatewayManagementEndpoint.GET_CHURCH)
    public GetChurchResult getChurch(@PathVariable String churchId) throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChruchQueryResultToGetChurchResult(churchExternalService.getChurch(churchId));
    }

    @GetMapping(AdminGatewayManagementEndpoint.GET_CHURCHES)
    public GetChurchesResult getChurches() throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChurchesQueryResultToGetChurchesResult(churchExternalService.getChurches());
    }

    @PostMapping(AdminGatewayManagementEndpoint.CREATE_CHURCH)
    public CreateChurchResult createChurch(@RequestBody CreateChurchRequest createChurchRequest) throws CommonException {
        return ChurchGatewayMapper.INSTANCE.createChurchCommandResultToCreateChurchResult(
            churchExternalService.createChurch(
                ChurchGatewayMapper.INSTANCE.createChurchRequestToCreateChurchCommand(createChurchRequest)
            )
        );
    }

    @DeleteMapping(AdminGatewayManagementEndpoint.DELETE_CHURCH)
    public void deleteChurch(@PathVariable String churchId) {
        churchExternalService.deleteChurch(churchId);
    }

    @PutMapping(AdminGatewayManagementEndpoint.UPDATE_CHURCH)
    public void updateChurch(@PathVariable String churchId, UpdateChurchRequest updateChurchRequest) throws CommonException {
        churchExternalService.updateChurch(churchId, ChurchGatewayMapper.INSTANCE.updateChurchRequestToUpdateChurchCommand(updateChurchRequest));
    }

}
