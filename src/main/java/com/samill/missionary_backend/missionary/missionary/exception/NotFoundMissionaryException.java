package com.samill.missionary_backend.missionary.missionary.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;

public class NotFoundMissionaryException extends CommonException {

    public NotFoundMissionaryException() {
        super(ResponseCode.NOT_FOUND_MISSIONARY_ERROR);
    }
}
