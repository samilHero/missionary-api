package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.church.dto.CreateChurchCommand;
import com.samill.missionary_backend.church.dto.CreateChurchCommandResult;
import com.samill.missionary_backend.church.dto.GetChurchQueryResult;
import com.samill.missionary_backend.church.dto.GetChurchesQueryResult;
import com.samill.missionary_backend.church.dto.UpdateChurchCommand;
import com.samill.missionary_backend.gateway.dto.CreateChurchRequest;
import com.samill.missionary_backend.gateway.dto.CreateChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchesResult;
import com.samill.missionary_backend.gateway.dto.UpdateChurchRequest;
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


