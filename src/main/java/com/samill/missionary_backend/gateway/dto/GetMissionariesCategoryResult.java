package com.samill.missionary_backend.gateway.dto;

public record GetMissionariesCategoryResult(
    GetMissionariesCategoryResultGroup domestic,
    GetMissionariesCategoryResultGroup abroad
) {

}
