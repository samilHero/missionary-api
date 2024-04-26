package com.samill.missionary_backend.gateway;

import com.samill.missionary_backend.church.church.dto.GetChurchQueryResult;
import com.samill.missionary_backend.gateway.dto.GetChurchResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChurchGatewayMapper {
    ChurchGatewayMapper INSTANCE = Mappers.getMapper(ChurchGatewayMapper.class);

    GetChurchResult getChruchQueryResultToGetChurchResult(GetChurchQueryResult getChurchQueryResult);

}
