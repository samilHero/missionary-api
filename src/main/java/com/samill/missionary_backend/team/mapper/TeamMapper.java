package com.samill.missionary_backend.team.mapper;

import com.samill.missionary_backend.team.dto.CreateTeamCommand;
import com.samill.missionary_backend.team.dto.UpdateTeamCommand;
import com.samill.missionary_backend.team.dto.UpdateTeamMemberCommand;
import com.samill.missionary_backend.team.entity.Team;
import com.samill.missionary_backend.team.entity.TeamMember;
import org.mapstruct.*;
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
}
