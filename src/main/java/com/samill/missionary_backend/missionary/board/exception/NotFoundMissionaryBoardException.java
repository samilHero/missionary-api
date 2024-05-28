package com.samill.missionary_backend.missionary.board.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;

public class NotFoundMissionaryBoardException extends MissionaryBoardException {

    public NotFoundMissionaryBoardException() {
        super(ResponseCode.NOT_FOUND_MISSIONARY_BOARD);
    }
}
