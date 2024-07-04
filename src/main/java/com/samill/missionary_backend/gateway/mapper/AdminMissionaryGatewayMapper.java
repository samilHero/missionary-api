package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResult;
import com.samill.missionary_backend.gateway.dto.GetMissionaryRegionsResultRegion;
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

    default GetMissionaryRegionsResult getMissionaryRegionsQueryResultToGetMissionaryRegionsResult(
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


}


