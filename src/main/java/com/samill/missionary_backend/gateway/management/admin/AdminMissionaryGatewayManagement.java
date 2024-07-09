package com.samill.missionary_backend.gateway.management.admin;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.AppointMissionaryStaffsRequest;
import com.samill.missionary_backend.gateway.dto.AppointMissionaryStaffsResult;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryRequest;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryResult;
import com.samill.missionary_backend.gateway.dto.DisappointMissionaryStaffsRequest;
import com.samill.missionary_backend.gateway.dto.GetAdminMissionariesResult;
import com.samill.missionary_backend.gateway.dto.GetAdminMissionaryResult;
import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResult;
import com.samill.missionary_backend.gateway.dto.UpdateAdminMissionaryRequest;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import com.samill.missionary_backend.gateway.mapper.admin.AdminMissionaryGatewayMapper;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        @RequestParam("regionId") @NonNull String regionId,
        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
        @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber
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
    public @NonNull GetAdminMissionaryResult getMissionary(
        @NonNull @PathVariable("missionaryId") String missionaryId
    ) throws CommonException {
        missionaryExternalService.getMissionary(new GetMissionaryQuery(missionaryId));

        return new GetAdminMissionaryResult();
    }

    @PostMapping(AdminGatewayManagementEndPoint.CREATE_MISSIONARY)
    public @NonNull CreateMissionaryResult createMissionary(@Valid @NonNull @RequestBody CreateMissionaryRequest createMissionaryRequest)
        throws CommonException {
        return AdminMissionaryGatewayMapper.INSTANCE.toCreateMissionaryResult(
            missionaryExternalService.createMissionary(
                // TODO: Implement MemberContext
                "89b1516a-d3c4-4da6-9262-20c0ec305a69",
                AdminMissionaryGatewayMapper.INSTANCE.toCreateMissionaryCommand(createMissionaryRequest)
            )
        );
    }

    @PutMapping(AdminGatewayManagementEndPoint.UPDATE_MISSIONARY)
    public void updateMissionary(
        @NonNull @PathVariable("missionaryId") String missionaryId,
        @Valid @NonNull @RequestBody UpdateAdminMissionaryRequest updateAdminMissionaryRequest
    ) throws CommonException {
        missionaryExternalService.updateMissionary(
            "89b1516a-d3c4-4da6-9262-20c0ec305a69",
            missionaryId,
            AdminMissionaryGatewayMapper.INSTANCE.toUpdateMissionaryCommand(updateAdminMissionaryRequest)
        );
    }

    @PostMapping(AdminGatewayManagementEndPoint.APPOINT_MISSIONARY_STAFF)
    public @NonNull AppointMissionaryStaffsResult appointMissionaryStaffs(
        @NonNull @PathVariable String missionaryId,
        @NonNull @RequestBody AppointMissionaryStaffsRequest request
    ) throws CommonException {
        return AdminMissionaryGatewayMapper.INSTANCE.toCreateMissionaryStaffsResult(
            missionaryExternalService.appointMissionaryStaffs(
                "89b1516a-d3c4-4da6-9262-20c0ec305a69",
                AdminMissionaryGatewayMapper.INSTANCE.toAppointMissionaryStaffsCommand(
                    missionaryId,
                    request
                )
            )
        );
    }

    @DeleteMapping(AdminGatewayManagementEndPoint.DISAPPOINT_MISSIONARY_STAFF)
    public void disappointMissionaryStaffs(
        @NonNull @PathVariable String missionaryId,
        @NonNull @RequestBody DisappointMissionaryStaffsRequest request
    ) throws CommonException {
        missionaryExternalService.disappointMissionaryStaffs(
            "89b1516a-d3c4-4da6-9262-20c0ec305a69",
            AdminMissionaryGatewayMapper.INSTANCE.toDisappointMissionaryStaffsCommand(
                missionaryId,
                request
            )
        );
    }
}
