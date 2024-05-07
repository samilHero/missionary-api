package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.CreateAdminRequest;
import com.samill.missionary_backend.gateway.dto.LoginAdminRequest;
import com.samill.missionary_backend.gateway.dto.LoginAdminResult;
import com.samill.missionary_backend.member.dto.CreateAdminCommand;
import com.samill.missionary_backend.member.dto.LoginAdminQuery;
import com.samill.missionary_backend.member.dto.LoginAdminQueryResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminGatewayMapper {

    AdminGatewayMapper INSTANCE = Mappers.getMapper(AdminGatewayMapper.class);

    CreateAdminCommand createAdminRequestToCreateAdminCommand(CreateAdminRequest createAdminRequest);

    LoginAdminQuery loginAdminRequestToLoginAdminQuery(LoginAdminRequest loginAdminRequest);

    LoginAdminResult loginAdminQueryResultToLoginAdminResult(LoginAdminQueryResult loginAdminQueryResult);

}


