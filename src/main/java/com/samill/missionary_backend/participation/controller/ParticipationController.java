package com.samill.missionary_backend.participation.controller;

import com.samill.missionary_backend.participation.ParticipationInternalService;
import com.samill.missionary_backend.participation.dto.ParticipationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
class ParticipationController {
    private final ParticipationInternalService participationInternalService;

    @PostMapping("/participate")
    public void participate(ParticipationDto participationDto) {
        participationInternalService.participate(participationDto);
    }
}
