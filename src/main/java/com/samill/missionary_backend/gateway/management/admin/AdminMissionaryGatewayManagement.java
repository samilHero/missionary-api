package com.samill.missionary_backend.gateway.management.admin;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.GetAdminMissionariesResult;
import com.samill.missionary_backend.gateway.dto.GetAdminMissionaryResult;
import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResult;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.admin.AdminMissionaryGatewayMapper;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminMissionaryGatewayManagement {

    private final MissionaryExternalService missionaryExternalService;

    @GetMapping(AdminGatewayManagementEndPoint.GET_MISSIONARY_REGIONS)
    public @NonNull GetMissionaryRegionsResult getMissionaryRegions() throws CommonException {
        return AdminMissionaryGatewayMapper.INSTANCE.getMissionaryRegionsQueryResultToGetMissionaryRegionsResult(
            missionaryExternalService.getMissionaryRegions()
        );
    }


    @GetMapping(AdminGatewayManagementEndPoint.GET_MISSIONARIES)
    public @NonNull GetAdminMissionariesResult getMissionaries(
        @RequestParam("region_id") @NonNull String regionId,
        @RequestParam(value = "page_size", required = false) Integer pageSize,
        @RequestParam(value = "page_number", required = false) Integer pageNumber
    ) {
        return AdminMissionaryGatewayMapper.INSTANCE.getMissionariesByRegionQueryResultToGetAdminMissionariesResult(
            missionaryExternalService.getMissionariesByRegion(
                new GetMissionariesByRegionQuery(
                    regionId,
                    pageSize,
                    pageNumber
                )
            )
        );
    }

    @GetMapping(AdminGatewayManagementEndPoint.GET_MISSIONARY)
    public @NonNull GetAdminMissionaryResult getMissionary(@NonNull @PathVariable("missionaryId") String missionaryId) throws CommonException {
        missionaryExternalService.getMissionary(new GetMissionaryQuery(missionaryId));

        return new GetAdminMissionaryResult();
    }
}
