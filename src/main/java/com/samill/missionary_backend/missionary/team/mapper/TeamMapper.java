package com.samill.missionary_backend.missionary.team.mapper;

import com.samill.missionary_backend.missionary.team.dto.*;
import com.samill.missionary_backend.missionary.team.entity.Team;
import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper( TeamMapper.class );

    Team createTeamCommandToEntity(CreateTeamCommand createTeamCommand);

    Team updateTeamCommandToEntity(UpdateTeamCommand updateTeamCommand);

    @Named("MEMBER")
    TeamMember updateTeamMemberCommandToEntity(UpdateTeamMemberCommand updateTeamMemberCommand);

    @IterableMapping(qualifiedByName = "MEMBER")
    List<TeamMember> updateTeamMemberCommandToEntity(List<UpdateTeamMemberCommand> updateTeamMemberCommandList);

    @Named("TEAM")
    GetTeamQueryResult entityToGetTeamQueryResult(Team team);

    @IterableMapping(qualifiedByName = "TEAM")
    List<GetTeamQueryResult> entityToGetTeamsQueryResult(List<Team> team);
}
