package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.common.dto.UserContext;
import com.samill.missionary_backend.member.MemberManagement;
import com.samill.missionary_backend.member.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberGatewayManagement {

    private final MemberManagement memberManagement;

    @PostMapping("/user")
    // controller parameter 에 Usercontext 를 받으면 token 정보를 받아올수 있습니다.
    public UserDto getUser(UserContext userContext) throws Exception {
        log.info(userContext.getUserId());
        return memberManagement.getUser();
    }

}
