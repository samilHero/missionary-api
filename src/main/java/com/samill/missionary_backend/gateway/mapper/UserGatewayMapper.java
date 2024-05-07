package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.CreateUserRequest;
import com.samill.missionary_backend.gateway.dto.GetUserResult;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserResult;
import com.samill.missionary_backend.member.dto.CreateUserCommand;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.dto.LoginUserQuery;
import com.samill.missionary_backend.member.dto.LoginUserQueryResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserGatewayMapper {

    UserGatewayMapper INSTANCE = Mappers.getMapper(UserGatewayMapper.class);

    LoginUserQuery loginUserRequestToLoginUserQuery(LoginUserRequest loginUserRequest);

    CreateUserCommand createUserRequestToCreateUserCommand(CreateUserRequest createUserRequest);

    LoginUserResult loginUserQueryResultToLoginUserResult(LoginUserQueryResult loginUserQueryResult);

    GetUserResult getUserDtoToGetUserResult(GetUserDto getUserDto);

}


