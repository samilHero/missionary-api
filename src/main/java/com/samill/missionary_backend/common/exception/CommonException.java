package com.samill.missionary_backend.common.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import lombok.NonNull;

public class CommonException extends Exception {

    private final ResponseCode responseCode;

    public CommonException(@NonNull ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return responseCode.getMessage();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public Integer getCode() {
        return responseCode.getCode();
    }


}
