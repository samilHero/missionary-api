package com.samill.missionary_backend.gateway.management;

import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.CREATE_PARTICIPATION;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.CREATE_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.DELETE_PARTICIPATION;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_IS_EXISTED_USER_ID_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_MISSIONARIES;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.UPDATE_PARTICIPATION;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;

import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.CreateUserRequest;
import com.samill.missionary_backend.gateway.dto.GetUserMissionariesRequest;
import com.samill.missionary_backend.gateway.dto.GetUserMissionariesResult;
import com.samill.missionary_backend.gateway.dto.GetUserMissionariesResultMissionary;
import com.samill.missionary_backend.gateway.dto.GetUserResult;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserResult;
import com.samill.missionary_backend.gateway.dto.Participation.CreateParticipationRequest;
import com.samill.missionary_backend.gateway.dto.Participation.DeleteParticipationRequest;
import com.samill.missionary_backend.gateway.dto.Participation.UpdateParticipationRequest;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.gateway.mapper.UserGatewayMapper;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.participation.dto.UpdateParticipationCommand;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(GET_IS_EXISTED_USER_ID_URI)
    public Boolean isExistedUserId(@PathVariable String loginId) {
        return memberManagement.isExistedUserByLoginId(loginId);
    }

    @GetMapping(GET_MISSIONARIES)
    public GetUserMissionariesResult getMissionaries(GetUserMissionariesRequest request) throws Exception {
        return new GetUserMissionariesResult(
            List.of(
                new GetUserMissionariesResultMissionary(
                    UUID.randomUUID().toString(),
                    "선교1",
                    OffsetDateTime.now(),
                    OffsetDateTime.now(),
                    "https://samil.com",
                    10,
                    20
                )
            ),
            null,
            false
        );
    }

    @PostMapping(CREATE_PARTICIPATION)
    public void createParticipation(CreateParticipationRequest createParticipationRequest, MemberContext memberContext) throws CommonException {
        createParticipationRequest.setUserInfo(memberContext);
        CreateParticipationCommand command = ParticipationGatewayMapper.INSTANCE.createParticipationToCreateParticipationCommand(createParticipationRequest);
        participationExternalService.createParticipation(command);
    }

    @PutMapping(UPDATE_PARTICIPATION)
    public void updateParticipation(@PathVariable String participationId, UpdateParticipationRequest updateParticipationRequest) throws CommonException {
        UpdateParticipationCommand command = ParticipationGatewayMapper.INSTANCE.updateParticipationToUpdateParticipationCommand(updateParticipationRequest);
        participationExternalService.updateParticipation(participationId, command);
    }

    @DeleteMapping(DELETE_PARTICIPATION)
    public void deleteParticipation(@PathVariable String participationId, DeleteParticipationRequest deleteParticipationRequest) throws CommonException {
        DeleteParticipationCommand command = ParticipationGatewayMapper.INSTANCE.deleteParticipationToDeleteParticipationCommand(deleteParticipationRequest);
        participationExternalService.deleteParticipation(participationId, command);
    }


}
