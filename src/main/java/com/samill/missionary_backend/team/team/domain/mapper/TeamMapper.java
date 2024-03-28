package com.samill.missionary_backend.team.team.domain.mapper;


import com.samill.missionary_backend.team.team.domain.dto.TeamDto;
import com.samill.missionary_backend.team.team.domain.model.Team;
import com.samill.missionary_backend.team.team.infrastructure.entity.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDto teamToTeamDto(Team team);

    Team teamEntityToTeam(TeamEntity teamEntity);
}
