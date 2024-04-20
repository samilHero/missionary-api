package com.samill.missionary_backend.participation.mapper;

import com.samill.missionary_backend.participation.dto.ParticipationDto;
import com.samill.missionary_backend.participation.entity.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper( ParticipationMapper.class );
    Participation dtoToEntity(ParticipationDto participationDto);
    ParticipationDto entityToDto(Participation participation);
}
