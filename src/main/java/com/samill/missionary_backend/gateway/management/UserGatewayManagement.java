package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.CreateUserRequest;
import com.samill.missionary_backend.gateway.dto.GetUserResult;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserResult;
import com.samill.missionary_backend.gateway.dto.Participation.CreateParticipation;
import com.samill.missionary_backend.gateway.dto.Participation.DeleteParticipation;
import com.samill.missionary_backend.gateway.dto.Participation.UpdateParticipation;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.UserGatewayMapper;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.participation.dto.UpdateParticipationCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class UserGatewayManagement {

    private final MemberExternalService memberManagement;
    private final ParticipationExternalService participationExternalService;

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

    @PostMapping(CREATE_PARTICIPATION)
    public void createParticipation(CreateParticipation createParticipation, MemberContext memberContext) throws CommonException {
        createParticipation.setUserInfo(memberContext);
        CreateParticipationCommand command = ParticipationGatewayMapper.INSTANCE.createParticipationToCreateParticipationCommand(createParticipation);
        participationExternalService.createParticipation(command);
    }

    @PutMapping(UPDATE_PARTICIPATION)
    public void updateParticipation(UpdateParticipation updateParticipation) throws CommonException {
        UpdateParticipationCommand command = ParticipationGatewayMapper.INSTANCE.updateParticipationToUpdateParticipationCommand(updateParticipation);
        participationExternalService.updateParticipation(command);
    }

    @DeleteMapping(DELETE_PARTICIPATION)
    public void deleteParticipation(DeleteParticipation deleteParticipation) throws CommonException {
        DeleteParticipationCommand command = ParticipationGatewayMapper.INSTANCE.deleteParticipationToDeleteParticipationCommand(deleteParticipation);
        participationExternalService.deleteParticipation(command);
    }
}
