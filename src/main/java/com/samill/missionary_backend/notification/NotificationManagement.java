package com.samill.missionary_backend.notification;

import com.samill.missionary_backend.member.MemberExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationManagement implements NotificationExternalService {

    private final MemberExternalService memberExternalService;

    @Override
    public void sendNotification() {
    }
}
