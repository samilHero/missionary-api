package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.member.MemberExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MissionaryManagement implements MissionaryExternalService {

    private final MemberExternalService memberExternalService;
    private final ChurchExternalService churchExternalService;


}
