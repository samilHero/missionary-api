package com.samill.missionary_backend.missionary.staff.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import lombok.NonNull;

public abstract class MissionaryStaffException extends MissionaryException {


    public MissionaryStaffException(@NonNull ResponseCode responseCode) {
        super(responseCode);
    }
}
