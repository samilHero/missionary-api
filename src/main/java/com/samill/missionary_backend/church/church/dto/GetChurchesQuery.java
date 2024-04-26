package com.samill.missionary_backend.church.church.dto;

public record GetChurchesQuery(
        GetChurchesQueryCursor cursor,

        Integer pageSize
) {

    @Override
    public Integer pageSize() {
        return pageSize == null ? 20 : pageSize;
    }
}
