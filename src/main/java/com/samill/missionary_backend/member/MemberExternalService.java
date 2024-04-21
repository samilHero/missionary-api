package com.samill.missionary_backend.member;

import com.samill.missionary_backend.member.dto.UserDto;

public interface MemberExternalService {

    // crud 기본
    void createUser();

    void createAdmin();

    // 회원정보 가져오기
    void getMember();

    UserDto getUser() throws Exception;

    void getUsers();

    boolean isExistUser();

    boolean isExistAdmin();

    boolean checkDuplicatedUser();
}
