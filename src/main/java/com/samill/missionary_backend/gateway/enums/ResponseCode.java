package com.samill.missionary_backend.gateway.enums;


import com.samill.missionary_backend.common.enums.EnumModel;

public enum ResponseCode implements EnumModel {
    COMMON_OK(0, "OK"),
    COMMON_BAD_REQUEST_ERROR(101, "Bad Request"),
    NOT_FOUND_ERROR(102, "Not Found"),
    ;
    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getKey() {
        return getCode().toString();
    }

    @Override
    public String getValue() {
        return getMessage();
    }

    public Integer getCode() { return code; }

    public String getMessage() { return message;}
}
