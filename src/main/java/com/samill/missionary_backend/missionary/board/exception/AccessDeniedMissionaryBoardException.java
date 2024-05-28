package com.samill.missionary_backend.missionary.board.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;

public class AccessDeniedMissionaryBoardException extends MissionaryBoardException {

    public AccessDeniedMissionaryBoardException() {
        super(ResponseCode.ACCESS_DENIED_MISSIONARY_BOARD);
    }
}
