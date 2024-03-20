package com.samill.missionaryBackend.gateway;

import com.samill.missionaryBackend.member.MemberDTO;
import com.samill.missionaryBackend.member.MemberExternalAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/member/api")
public class MemberGatewayManagement {

    private MemberExternalAPI memberExternalAPI;

    @GetMapping("/login")
    public MemberDTO login() {
        return memberExternalAPI.login();
    }

    @GetMapping("/signUp")
    public MemberDTO signUp() {
        return memberExternalAPI.signUp();
    }

    @GetMapping("/update")
    public MemberDTO update() {
        return memberExternalAPI.update();
    }

}