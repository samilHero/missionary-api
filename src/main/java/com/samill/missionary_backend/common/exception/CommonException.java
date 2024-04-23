package com.samill.missionary_backend.common.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class CommonException extends Exception {

    private ResponseCode responseCode;

    public CommonException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
