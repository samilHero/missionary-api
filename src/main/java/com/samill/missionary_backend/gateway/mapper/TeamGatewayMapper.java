package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.CreateTeamRequest;
import com.samill.missionary_backend.gateway.dto.GetTeamResult;
import com.samill.missionary_backend.gateway.dto.UpdateTeamMemberRequest;
import com.samill.missionary_backend.gateway.dto.UpdateTeamRequest;
import com.samill.missionary_backend.missionary.team.dto.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamGatewayMapper {
    TeamGatewayMapper INSTANCE = Mappers.getMapper( TeamGatewayMapper.class );

    CreateTeamCommand createTeamRequestToCreateTeamCommand(CreateTeamRequest createTeamRequest);

    UpdateTeamCommand updateTeamRequestToUpdateTeamCommand(UpdateTeamRequest updateTeamRequest);

    @Named("MEMBER")
    UpdateTeamMemberCommand updateTeamMemberRequestToUpdateTeamMemberCommand(UpdateTeamMemberRequest updateTeamMemberRequest);

    @IterableMapping(qualifiedByName = "MEMBER")
    List<UpdateTeamMemberCommand> updateTeamMEmberRequestsToUpdateTeamMEmberCommands(List<UpdateTeamMemberRequest> updateTeamMemberRequests);

    @Named("TEAM")
    GetTeamResult GetTeamQueryResultToGetTeamResult(GetTeamQueryResult getTeamQueryResult);

    @IterableMapping(qualifiedByName = "TEAM")
    List<GetTeamResult> GetTeamQueryResultsToGetTeamResults(List<GetTeamQueryResult> getTeamQueryResults);
}
