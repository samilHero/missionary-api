package com.samill.missionary_backend.member;


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
