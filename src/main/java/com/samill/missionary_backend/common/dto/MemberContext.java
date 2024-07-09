package com.samill.missionary_backend.common.dto;

import com.samill.missionary_backend.common.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberContext {

    private String memberId;
    private String id;
    private ServiceType serviceType;
    private String name;
}