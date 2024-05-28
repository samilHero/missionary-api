package com.samill.missionary_backend.missionary.board.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import lombok.NonNull;

public abstract class MissionaryBoardException extends MissionaryException {

    public MissionaryBoardException(@NonNull ResponseCode responseCode) {
        super(responseCode);
    }
}
