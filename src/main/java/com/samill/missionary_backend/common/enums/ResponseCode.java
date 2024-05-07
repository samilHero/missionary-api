package com.samill.missionary_backend.common.enums;


public enum ResponseCode implements EnumModel {
    COMMON_OK(0, "OK"),
    COMMON_BAD_REQUEST_ERROR(101, "Bad Request"),
    NOT_FOUND_ERROR(102, "Not Found"),
    NOT_FOUND_CHURCH_ERROR(700, "존재하지 않는 교회입니다."),
    ALREADY_EXITED_USER_ERROR(800, "사용할 수 없는 아이디 입니다."),

    IS_NOT_EXITED_LOGIN_ID_ERROR(801, "등록되지 않은 아이디 입니다."),

    INVALID_LOGIN_PASSWORD_ERROR(802, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_MISSIONARY_ERROR(900, "존재하지 않는 선교입니다."),

    INVALID_PARTICIPATION_PERIOD(901, "참여 기간이 올바르지 않습니다."),
    INVALID_WORK_PERIOD(902, "활동 기간이 올바르지 않습니다."),

    NOT_FOUND_MISSIONARY_STAFF(999, "존재 하지 않는 스태프입니다."),

    ;

    private final Integer code;
    private final String message;

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
