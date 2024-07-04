package com.samill.missionary_backend.gateway.mapper.admin;

import com.samill.missionary_backend.gateway.dto.GetAdminMissionariesResult;
import com.samill.missionary_backend.gateway.dto.GetAdminMissionariesResultMissionary;
import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResult;
import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResultRegion;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQueryResultMissionary;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResultRegion;
import java.util.List;
import java.util.function.Function;
import lombok.NonNull;
import org.mapstruct.Mapper;
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


}


