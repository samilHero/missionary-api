package com.samill.missionary_backend.common.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor(
        access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@Embeddable
public class Address {

    private String basic;
    private String detail;


    @Access(AccessType.FIELD)
    public String getFullAddress() {
        return basic + " " + detail;
    }


}
