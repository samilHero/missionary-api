package com.samill.missionaryBackend;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class MissionaryBackendController {

    @GetMapping("/")
    String printHelloWorld(){
        return "helloWorld";
    }
}
