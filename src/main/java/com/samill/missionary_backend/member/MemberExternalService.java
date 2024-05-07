package com.samill.missionary_backend.member;

import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.dto.PostTokenDto;
import com.samill.missionary_backend.member.dto.PostTokenRequest;
import com.samill.missionary_backend.member.dto.PutUserRequest;
import com.samill.missionary_backend.member.exception.MemberException;

public interface MemberExternalService {

    // crud 기본
    void createUser(PutUserRequest request) throws MemberException;

    PostTokenDto login(PostTokenRequest request) throws MemberException;

    void createAdmin();

    // 회원정보 가져오기
    void getMember();

    GetUserDto getUserById(String userId) throws Exception;

    GetUserDto getUserByLoginId(String loginId) throws Exception;

    GetUserDto getUserByMemberId(String memberId) throws Exception;

    void getUsers();

    boolean isExistedUserByLoginId(String loginId);

    boolean isExistAdmin();
}
