package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.Participation.*;
import com.samill.missionary_backend.missionary.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParticipationGatewayMapper {
    ParticipationGatewayMapper INSTANCE = Mappers.getMapper( ParticipationGatewayMapper.class );
    GetParticipationsQuery getParticipationsToGetParticipationsQuery(GetParticipations getParticipation);
    @Named("ResultList")
    GetParticipationResult getParticipationQueryResultToGetParticipationResult(GetParticipationQueryResult getParticipationQueryResult);
    CreateParticipationCommand createParticipationToCreateParticipationCommand(CreateParticipation createParticipation);
    UpdateParticipationCommand updateParticipationToUpdateParticipationCommand(UpdateParticipation updateParticipation);
    DeleteParticipationCommand deleteParticipationToDeleteParticipationCommand(DeleteParticipation deleteParticipation);
}
