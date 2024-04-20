package com.samill.missionary_backend.church.church.exception;

import com.samill.missionary_backend.common.exception.BaseException;

public class ChurchNotFoundException extends BaseException {
    public ChurchNotFoundException() {
        super(404, "Church not found", "The church you are looking for does not exist.");
    }
}
