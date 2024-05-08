package com.samill.missionary_backend.participation.mapper;

import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.dto.UpdateParticipationCommand;
import com.samill.missionary_backend.participation.entity.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper( ParticipationMapper.class );
    Participation createParticipationCommandToEntity(CreateParticipationCommand participationDto);
    GetParticipationQueryResult entityToGetParticipationQueryResult(Participation participation);
    Participation updateParticipationCommandToEntity(UpdateParticipationCommand updateParticipationCommand);
}
