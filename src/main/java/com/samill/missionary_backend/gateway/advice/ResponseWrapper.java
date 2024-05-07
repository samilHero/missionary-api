package com.samill.missionary_backend.gateway.advice;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    /**
     * “Allows customizing the response after the execution of an @ResponseBody
     * or a ResponseEntity controller method
     * but before the body is written with an HttpMessageConverter.”
     * <p>
     * 컨트롤러 메서드가 @ResponseBody나 ResponseEntity로 반환할 때의 응답을 커스터마이징 할 수 있다.
     * 이는 메시지 컨버터가 해당 응답 객체를 변환하기 전에 일어난다.
     */

    @Override
    public boolean supports(
        MethodParameter returnType,
        Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {

        if (body instanceof ProblemDetail problemDetail) {
            return ApiResponse.builder()
                .statusCode(problemDetail.getStatus())
                .message(problemDetail.getDetail())
                .build();
        }
        if (body instanceof CommonException commonException) {
            return ApiResponse.builder()
                .statusCode(commonException.getCode())
                .message(commonException.getMessage())
                .build();
        }
        if (body instanceof Exception) {
            return ApiResponse.builder()
                .statusCode(ResponseCode.COMMON_BAD_REQUEST_ERROR.getCode())
                .message(ResponseCode.COMMON_BAD_REQUEST_ERROR.getMessage())
                .build();
        }

        return ApiResponse.builder()
            .statusCode(ResponseCode.COMMON_OK.getCode())
            .message(ResponseCode.COMMON_OK.getMessage())
            .data(body)
            .build();
    }
}
