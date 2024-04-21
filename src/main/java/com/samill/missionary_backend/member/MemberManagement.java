package com.samill.missionary_backend.member;


import com.samill.missionary_backend.common.exception.BaseException;
import com.samill.missionary_backend.gateway.enums.ResponseCode;
import com.samill.missionary_backend.gateway.exception.CommonException;
import com.samill.missionary_backend.member.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberManagement implements MemberExternalService {

    @Override
    public void createUser() {

    }

    @Override
    public void createAdmin() {

    }

    @Override
    public void getMember() {

    }

    @Override
    public UserDto getUser() throws Exception {
        return UserDto.builder()
                .userId("hanbyul.jung")
                .username("정한별")
                .build();
    }

    @Override
    public void getUsers() {

    }

    @Override
    public boolean isExistUser() {
        return false;
    }

    @Override
    public boolean isExistAdmin() {
        return false;
    }

    @Override
    public boolean checkDuplicatedUser() {
        return false;
    }
}
