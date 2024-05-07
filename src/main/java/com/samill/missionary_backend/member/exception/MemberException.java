package com.samill.missionary_backend.member.exception;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberException extends CommonException {

    private ResponseCode responseCode;

    @Builder(builderMethodName = "writeMemberExceptionBuilder")
    public MemberException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
