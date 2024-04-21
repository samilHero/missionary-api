package com.samill.missionary_backend.participation.infrastructure.mapper;

import com.samill.missionary_backend.participation.domain.dto.ParticipationDto;
import com.samill.missionary_backend.participation.infrastructure.entity.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper( ParticipationMapper.class );
//    @Mapping(target = "participant", source = "participant")
    Participation dtoToEntity(ParticipationDto participationDto);
//    ParticipationDto entityToDto(Participation participation);
}
