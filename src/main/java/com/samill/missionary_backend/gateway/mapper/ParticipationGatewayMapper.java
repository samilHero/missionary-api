package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.Participation.*;
import com.samill.missionary_backend.participation.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParticipationGatewayMapper {
    ParticipationGatewayMapper INSTANCE = Mappers.getMapper( ParticipationGatewayMapper.class );
    GetParticipationsQuery getParticipationsToGetParticipationsQuery(GetParticipationsResult getParticipation);
    @Named("ResultList")
    GetParticipationResult getParticipationQueryResultToGetParticipationResult(GetParticipationQueryResult getParticipationQueryResult);
    CreateParticipationCommand createParticipationToCreateParticipationCommand(CreateParticipationRequest createParticipationRequest);
    UpdateParticipationCommand updateParticipationToUpdateParticipationCommand(UpdateParticipationRequest updateParticipationRequest);
    DeleteParticipationCommand deleteParticipationToDeleteParticipationCommand(DeleteParticipationRequest deleteParticipationRequest);
}
