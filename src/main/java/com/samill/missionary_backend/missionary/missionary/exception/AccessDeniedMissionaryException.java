package com.samill.missionary_backend.missionary.missionary.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.missionary.exception.MissionaryException;

public class AccessDeniedMissionaryException extends MissionaryException {

    public AccessDeniedMissionaryException() {
        super(ResponseCode.ACCESS_DENIED_MISSIONARY);
    }
}
