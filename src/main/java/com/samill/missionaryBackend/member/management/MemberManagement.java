package com.samill.missionaryBackend.member.management;

import com.samill.missionaryBackend.member.MemberDTO;
import com.samill.missionaryBackend.member.MemberExternalAPI;
import com.samill.missionaryBackend.member.MemberInternalAPI;
import com.samill.missionaryBackend.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@org.springframework.stereotype.Service
@NoArgsConstructor
@AllArgsConstructor
public class MemberManagement implements MemberInternalAPI, MemberExternalAPI {

    private MemberService memberService;


    @Override
    @Transactional
    public MemberDTO add(MemberDTO employee) {
        return null;
    }

    @Override
    public MemberDTO login() {
        return memberService.login();
    }

    @Override
    public MemberDTO signUp() {
        return memberService.signUp();
    }

    @Override
    public MemberDTO update() {
        memberService.update();
        return null;
    }

}