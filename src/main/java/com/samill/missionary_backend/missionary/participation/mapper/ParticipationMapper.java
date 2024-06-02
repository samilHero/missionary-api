package com.samill.missionary_backend.missionary.participation.mapper;

import com.samill.missionary_backend.missionary.dto.CreateParticipationCommand;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.UpdateParticipationCommand;
import com.samill.missionary_backend.missionary.participation.entity.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper( ParticipationMapper.class );
    Participation createParticipationCommandToEntity(CreateParticipationCommand participationDto);
    GetParticipationQueryResult entityToGetParticipationQueryResult(Participation participation);
    Participation updateParticipationCommandToEntity(UpdateParticipationCommand updateParticipationCommand);
}
