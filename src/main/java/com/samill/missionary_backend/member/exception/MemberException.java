package com.samill.missionary_backend.member.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberException extends CommonException {


    @Builder(builderMethodName = "writeMemberExceptionBuilder")
    public MemberException(ResponseCode responseCode) {
        super(responseCode);
    }
}
