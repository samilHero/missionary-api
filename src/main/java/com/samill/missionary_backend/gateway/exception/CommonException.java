package com.samill.missionary_backend.gateway.exception;

import com.samill.missionary_backend.gateway.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class CommonException  extends  Exception {
    private ResponseCode responseCode;

    public CommonException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
