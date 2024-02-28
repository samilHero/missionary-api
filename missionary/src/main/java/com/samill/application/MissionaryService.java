package com.samill.application;

import com.samill.missionaryRegistration.application.MissionaryRegistartion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MissionaryService {

    private MissionaryUserServiceInterface missionaryUserService;


    public String sayHelloWithUser(){
        System.out.println("Hello MissionaryService");
        return this.missionaryUserService.sayHello();
    }


    public MissionaryRegistartion register() {
        return null;
    }
}