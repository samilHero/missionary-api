package com.samill.missionary_backend.common.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@Embeddable
public class Pastor {

    private String name;

    private String phone;


}
