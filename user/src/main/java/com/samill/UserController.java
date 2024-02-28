package com.samill;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/hello")
    String sayHello(){
        return this.userService.sayHello();
    }
}
