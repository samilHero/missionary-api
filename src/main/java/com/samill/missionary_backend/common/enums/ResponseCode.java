package com.samill.missionary_backend.common.enums;


public enum ResponseCode implements EnumModel {
    COMMON_OK(0, "OK"),
    COMMON_BAD_REQUEST_ERROR(101, "Bad Request"),
    NOT_FOUND_ERROR(102, "Not Found"),
    PARTICIPATION_MAXIMUM_EXCEEDED(1101, "선교신청 가능한 정원이 가득찼습니다."),
    PARTICIPATION_ALREADY_PARTICIPATED(1101, "선교에 이미 신청되었습니다."),
    PARTICIPATION_NOT_ENROLLED(1102, "선교에 신청되어 있지 않습니다."),
    PARTICIPATION_NOT_FOUND(1104, "존재하지 않는 신청내역입니다.")
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

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
