package com.samill.missionary_backend.missionary.board.aop;

import com.samill.missionary_backend.missionary.staff.service.MissionaryStaffService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MissionaryBoardUserAspect {

    private final MissionaryStaffService missionaryStaffService;

    @Before("@annotation(com.samill.missionary_backend.missionary.board.annotation.ParticipationChecker)")

    public void checkIsParticipated(JoinPoint joinPoint) {

        joinPoint.getArgs();
    }
}