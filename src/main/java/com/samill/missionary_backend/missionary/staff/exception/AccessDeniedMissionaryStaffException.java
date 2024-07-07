package com.samill.missionary_backend.missionary.staff.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;

public class AccessDeniedMissionaryStaffException extends MissionaryStaffException {

    public AccessDeniedMissionaryStaffException() {
        super(ResponseCode.ACCESS_DENIED_MISSIONARY_BOARD);
    }
}
