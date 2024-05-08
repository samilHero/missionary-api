package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.gateway.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChurchGatewayMapper {
    ChurchGatewayMapper INSTANCE = Mappers.getMapper(ChurchGatewayMapper.class);

    GetChurchResult getChruchQueryResultToGetChurchResult(GetChurchQueryResult getChurchQueryResult);

    GetChurchesResult getChurchesQueryResultToGetChurchesResult(GetChurchesQueryResult getChurchesQueryResult);

    CreateChurchResult createChurchCommandResultToCreateChurchResult(CreateChurchCommandResult createChurchCommandResult);

    CreateChurchCommand createChurchRequestToCreateChurchCommand(CreateChurchRequest createChurchRequest);

    UpdateChurchCommand updateChurchRequestToUpdateChurchCommand(UpdateChurchRequest updateChurchRequest);
}


