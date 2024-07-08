package com.samill.missionary_backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersQuery {

    private String name;
    @Builder.Default
    private Integer pageSize = 20;
    @Builder.Default
    private Integer pageNo = 0;
}
