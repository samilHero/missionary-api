package com.samill.missionary_backend.gateway.mapper.admin;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.gateway.dto.AppointMissionaryStaffsRequest;
import com.samill.missionary_backend.gateway.dto.AppointMissionaryStaffsRequestStaff;
import com.samill.missionary_backend.gateway.dto.AppointMissionaryStaffsResult;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryRequest;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryResult;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryStaffsResultStaff;
import com.samill.missionary_backend.gateway.dto.DisappointMissionaryStaffsRequest;
import com.samill.missionary_backend.gateway.dto.GetAdminMissionariesResult;
import com.samill.missionary_backend.gateway.dto.GetAdminMissionariesResultMissionary;
import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResult;
import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResultRegion;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandResult;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandResultStaff;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.DisappointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQueryResultMissionary;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResultRegion;
import java.util.List;
import java.util.function.Function;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMissionaryGatewayMapper {

    AdminMissionaryGatewayMapper INSTANCE = Mappers.getMapper(AdminMissionaryGatewayMapper.class);

    default @NonNull GetMissionaryRegionsResult getMissionaryRegionsQueryResultToGetMissionaryRegionsResult(
        GetMissionaryRegionsQueryResult getMissionaryRegionsQueryResult
    ) {

        final Function<@NonNull List<GetMissionaryRegionsQueryResultRegion>, @NonNull List<GetMissionaryRegionsResultRegion>>
            regionsToResultRegions = (List<GetMissionaryRegionsQueryResultRegion> resultRegions) ->
            resultRegions.stream()
                .map(resultRegion -> new GetMissionaryRegionsResultRegion(resultRegion.id(), resultRegion.name()))
                .toList();

        return new GetMissionaryRegionsResult(
            regionsToResultRegions.apply(getMissionaryRegionsQueryResult.domestic()),
            regionsToResultRegions.apply(getMissionaryRegionsQueryResult.abroad())
        );
    }

    default @NonNull GetAdminMissionariesResult getMissionariesByRegionQueryResultToGetAdminMissionariesResult(
        @NonNull GetMissionariesByRegionQueryResult getMissionariesByRegionQueryResult
    ) {

        final Function<@NonNull GetMissionariesByRegionQueryResultMissionary, @NonNull GetAdminMissionariesResultMissionary>
            missionaryToResultMissionary = (GetMissionariesByRegionQueryResultMissionary missionary) ->
            new GetAdminMissionariesResultMissionary(
                missionary.id(),
                missionary.name(),
                missionary.pastorName(),
                missionary.startDate(),
                missionary.endDate()
            );

        return new GetAdminMissionariesResult(
            getMissionariesByRegionQueryResult.missionaries().stream()
                .map(missionaryToResultMissionary)
                .toList(),
            getMissionariesByRegionQueryResult.totalCount(),
            getMissionariesByRegionQueryResult.totalPages(),
            getMissionariesByRegionQueryResult.currentPage()
        );
    }

    @Mapping(target = "regionId", source = "missionaryRegionId")
    @NonNull CreateMissionaryCommand toCreateMissionaryCommand(@NonNull CreateMissionaryRequest createMissionaryRequest);

    @NonNull CreateMissionaryResult toCreateMissionaryResult(@NonNull CreateMissionaryCommandResult createMissionaryCommandResult);

    default @NonNull AppointMissionaryStaffsCommand toAppointMissionaryStaffsCommand(
        @NonNull String missionaryId,
        @NonNull AppointMissionaryStaffsRequest appointMissionaryStaffsRequest
    ) {

        final Function<AppointMissionaryStaffsRequestStaff, AppointMissionaryStaffsCommandStaff> staffToCommandStaff =
            (AppointMissionaryStaffsRequestStaff staff) -> new AppointMissionaryStaffsCommandStaff(
                staff.userId(),
                staff.role()
            );

        return new AppointMissionaryStaffsCommand(
            missionaryId,
            appointMissionaryStaffsRequest.staffs().stream()
                .map(staffToCommandStaff)
                .toList()
        );
    }

    default @NonNull AppointMissionaryStaffsResult toCreateMissionaryStaffsResult(
        @NonNull AppointMissionaryStaffsCommandResult appointMissionaryStaffsCommand) {

        final Function<@NonNull AppointMissionaryStaffsCommandResultStaff, @NonNull CreateMissionaryStaffsResultStaff>
            toCreateMissionaryStaffsResultStaff = (AppointMissionaryStaffsCommandResultStaff staff) -> new CreateMissionaryStaffsResultStaff(
            staff.id(),
            staff.userId(),
            staff.role()
        );

        return new AppointMissionaryStaffsResult(
            appointMissionaryStaffsCommand.missionaryStaffs().stream()
                .map(toCreateMissionaryStaffsResultStaff)
                .toList()
        );
    }

    @NonNull DisappointMissionaryStaffsCommand toDisappointMissionaryStaffsCommand(
        @NonNull String missionaryId,
        @NonNull DisappointMissionaryStaffsRequest disappointMissionaryStaffsRequest
    );
}


