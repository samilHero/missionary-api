package com.samill.missionary_backend.church.church.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;

public class NotFoundChurchException extends CommonException {

    public NotFoundChurchException() {
        super(ResponseCode.NOT_FOUND_CHURCH_ERROR);
    }

}
