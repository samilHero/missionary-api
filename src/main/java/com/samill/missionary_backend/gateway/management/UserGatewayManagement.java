package com.samill.missionary_backend.gateway.management;

import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.CREATE_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;

import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.gateway.dto.CreateUserRequest;
import com.samill.missionary_backend.gateway.dto.GetUserResult;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserResult;
import com.samill.missionary_backend.gateway.mapper.UserGatewayMapper;
import com.samill.missionary_backend.member.MemberExternalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class UserGatewayManagement {

    private final MemberExternalService memberManagement;

    @GetMapping(GET_USER_URI)
    // controller parameter 에 Usercontext 를 받으면 token 정보를 받아올수 있습니다.
    public GetUserResult getUser(MemberContext memberContext) throws Exception {
        return UserGatewayMapper.INSTANCE.getUserDtoToGetUserResult(
            memberManagement.getUserById(memberContext.getUserId())
        );
    }

    @PostMapping(CREATE_USER_URI)
    public void signUp(@Valid @RequestBody CreateUserRequest request) throws Exception {
        memberManagement.createUser(
            UserGatewayMapper.INSTANCE.createUserRequestToCreateUserCommand(request)
        );
    }

    @PostMapping(USER_LOGIN_URI)
    public LoginUserResult login(@Valid @RequestBody LoginUserRequest request) throws Exception {
        return UserGatewayMapper.INSTANCE.loginUserQueryResultToLoginUserResult(
            memberManagement.loginUser(
                UserGatewayMapper.INSTANCE.loginUserRequestToLoginUserQuery(request)
            )
        );
    }

}
