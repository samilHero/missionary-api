package com.samill.missionary_backend.missionary.missionary.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;

public class InvalidParticipationPeriodException extends CommonException {

    public InvalidParticipationPeriodException() {
        super(ResponseCode.INVALID_PARTICIPATION_PERIOD);
    }
}
