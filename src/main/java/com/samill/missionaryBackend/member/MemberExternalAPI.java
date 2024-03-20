package com.samill.missionaryBackend.member;

public interface MemberExternalAPI {

    MemberDTO add(MemberDTO employee);

    MemberDTO login();

    MemberDTO signUp();

    MemberDTO update();
}