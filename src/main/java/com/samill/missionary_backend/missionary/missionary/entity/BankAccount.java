package com.samill.missionary_backend.missionary.missionary.entity;

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
public class BankAccount {

    private String bankName;
    private String placeHolder;
    private String number;
}
