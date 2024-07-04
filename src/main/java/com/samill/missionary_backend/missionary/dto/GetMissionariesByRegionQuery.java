package com.samill.missionary_backend.missionary.dto;

public record GetMissionariesByRegionQuery(
    String regionId,
    Integer pageSize,
    Integer pageNumber
) {

}
