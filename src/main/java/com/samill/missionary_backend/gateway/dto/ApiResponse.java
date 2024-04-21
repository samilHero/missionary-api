package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;

    @Builder
    private ApiResponse(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
