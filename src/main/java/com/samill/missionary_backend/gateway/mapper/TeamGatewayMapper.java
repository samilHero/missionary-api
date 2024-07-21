package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.CreateTeamRequest;
import com.samill.missionary_backend.gateway.dto.GetTeamListRequest;
import com.samill.missionary_backend.gateway.dto.GetTeamResult;
import com.samill.missionary_backend.gateway.dto.GetTeamResults;
import com.samill.missionary_backend.gateway.dto.UpdateTeamMemberRequest;
import com.samill.missionary_backend.gateway.dto.UpdateTeamRequest;
import com.samill.missionary_backend.missionary.dto.CreateTeamCommand;
import com.samill.missionary_backend.missionary.dto.GetTeamQueryResult;
import com.samill.missionary_backend.missionary.dto.UpdateTeamCommand;
import com.samill.missionary_backend.missionary.dto.UpdateTeamMemberCommand;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQuery;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQueryResult;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamGatewayMapper {

    TeamGatewayMapper INSTANCE = Mappers.getMapper(TeamGatewayMapper.class);

    CreateTeamCommand createTeamRequestToCreateTeamCommand(CreateTeamRequest createTeamRequest);

    UpdateTeamCommand updateTeamRequestToUpdateTeamCommand(UpdateTeamRequest updateTeamRequest);

    GetTeamListQuery getTeamListRequestToGetTeamListQuery(GetTeamListRequest getTeamListRequest);

    @Named("MEMBER")
    UpdateTeamMemberCommand updateTeamMemberRequestToUpdateTeamMemberCommand(UpdateTeamMemberRequest updateTeamMemberRequest);

    @IterableMapping(qualifiedByName = "MEMBER")
    List<UpdateTeamMemberCommand> updateTeamMEmberRequestsToUpdateTeamMEmberCommands(List<UpdateTeamMemberRequest> updateTeamMemberRequests);

    @Mapping(source = "getTeamQueryResult.teamMemberList", target = "teamMemberList")
    GetTeamResult GetTeamQueryResultToGetTeamResult(GetTeamQueryResult getTeamQueryResult);

    @Named("TEAMS")
    GetTeamResults GetTeamListQueryResultToGetTeamResult(GetTeamListQueryResult getTeamListQueryResult);

    @IterableMapping(qualifiedByName = "TEAMS")
    List<GetTeamResults> GetTeamListQueryResultsToGetTeamResults(List<GetTeamListQueryResult> getTeamListQueryResults);
}
