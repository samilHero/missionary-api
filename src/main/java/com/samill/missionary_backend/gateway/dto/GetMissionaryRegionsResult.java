package com.samill.missionary_backend.gateway.dto;

import java.util.List;

public record GetMissionaryRegionsResult(
    List<GetMissionaryRegionsResultRegion> domestic,
    List<GetMissionaryRegionsResultRegion> abroad
) {

}
