package com.samill.missionary_backend.member;

import com.samill.missionary_backend.member.dto.CreateAdminCommand;
import com.samill.missionary_backend.member.dto.CreateUserCommand;
import com.samill.missionary_backend.member.dto.GetAdminDto;
import com.samill.missionary_backend.member.dto.GetMemberServiceTypeDto;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.dto.LoginAdminQuery;
import com.samill.missionary_backend.member.dto.LoginAdminQueryResult;
import com.samill.missionary_backend.member.dto.LoginUserQuery;
import com.samill.missionary_backend.member.dto.LoginUserQueryResult;
import com.samill.missionary_backend.member.exception.MemberException;

public interface MemberExternalService {

    // crud 기본
    void createUser(CreateUserCommand request) throws MemberException;

    LoginUserQueryResult loginUser(LoginUserQuery request) throws MemberException;

    LoginAdminQueryResult loginAdmin(LoginAdminQuery request) throws Exception;

    void createAdmin(CreateAdminCommand createAdminCommand) throws MemberException;

    // 회원정보 가져오기
    void getMember();

    GetUserDto getUserById(String userId) throws Exception;

    GetUserDto getUserByLoginId(String loginId) throws Exception;

    GetAdminDto getAdminByLoginId(String loginId) throws Exception;

    GetUserDto getUserByMemberId(String memberId) throws MemberException;

    void getUsers();

    GetMemberServiceTypeDto getMemberServiceType(String memberId) throws MemberException;

    Boolean isExistedUserByLoginId(String userId);
}
