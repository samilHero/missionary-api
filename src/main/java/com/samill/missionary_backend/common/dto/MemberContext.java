package com.samill.missionary_backend.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberContext {

    private String memberId;
    private String userId;
    private String name;
}