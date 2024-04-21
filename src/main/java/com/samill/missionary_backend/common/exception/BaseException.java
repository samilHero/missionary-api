package com.samill.missionary_backend.common.exception;

public abstract class BaseException extends Exception {


    final Integer statusCode;
    final String message;

    BaseException(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}