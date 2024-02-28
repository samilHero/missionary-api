package com.samill.presentation;

import com.samill.application.MissionaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/missionary")
@AllArgsConstructor
public class MissionaryController {

    private MissionaryService missionaryService;

    @GetMapping("/hello")
    String sayHello(){
        return this.missionaryService.sayHelloWithUser();
    }
}
