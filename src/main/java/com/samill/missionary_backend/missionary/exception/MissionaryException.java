package com.samill.missionary_backend.missionary.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import lombok.NonNull;

public class MissionaryException extends CommonException {

    public MissionaryException(@NonNull ResponseCode responseCode) {
        super(responseCode);
    }
}
