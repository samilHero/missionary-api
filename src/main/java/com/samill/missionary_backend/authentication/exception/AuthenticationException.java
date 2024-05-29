package com.samill.missionary_backend.authentication.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class AuthenticationException extends CommonException {


    public AuthenticationException(@NonNull ResponseCode responseCode) {
        super(responseCode);
    }
}
