package com.samill.missionary_backend.common.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Getter
@Builder
public class CommonException extends Exception {

    private ResponseCode responseCode;

    public CommonException(@NonNull ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return responseCode.getMessage();
    }
}
