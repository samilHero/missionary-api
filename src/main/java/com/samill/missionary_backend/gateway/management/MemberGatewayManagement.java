package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.member.MemberManagement;
import com.samill.missionary_backend.member.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberGatewayManagement {

    private final MemberManagement memberManagement;

    @PostMapping("/user")
    public UserDto getUser() throws Exception {
        return  memberManagement.getUser();
    }

}
