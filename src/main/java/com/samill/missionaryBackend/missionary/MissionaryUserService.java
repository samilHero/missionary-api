package com.samill.missionaryBackend.missionary;

import com.samill.application.MissionaryUserServiceInterface;
import com.samill.User;
import com.samill.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MissionaryUserService implements MissionaryUserServiceInterface {


    private UserService userService;
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public String sayHello() {
        return this.userService.sayHello();
    }

    @Override
    public String getUserId() {
        return "UserId";
    }


}
