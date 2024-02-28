package com.samill;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    public String sayHello(){
        return "Hello User Service";
    }
}
