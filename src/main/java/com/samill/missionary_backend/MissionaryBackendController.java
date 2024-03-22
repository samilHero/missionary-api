package com.samill.missionary_backend;

import com.samill.missionary_backend.participation.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class MissionaryBackendController {

    private final ParticipationService participationService;

    @GetMapping("/")
    String printHelloWorld() {
        participationService.participate("missionaryId");
        return "helloWorld";
    }
}
