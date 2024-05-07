package com.samill.missionary_backend.missionary.staff.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;

public class NotFoundMissionaryStaffException extends CommonException {

    public NotFoundMissionaryStaffException() {
        super(ResponseCode.NOT_FOUND_MISSIONARY_STAFF);
    }

}
