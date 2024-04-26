package com.samill.missionary_backend.gateway.dto;

public record GetChurchesRequest(
        GetChurchesCursor cursor,

        Integer pageSize
) {

    @Override
    public Integer pageSize() {
        return pageSize == null ? 20 : pageSize;
    }
}
