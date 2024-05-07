package com.samill.missionary_backend.missionary.church.dto;

import java.util.List;
import lombok.NonNull;

public record GetMissionaryChurchesResult(
    @NonNull List<GetMissionaryChurchesMissionaryChurch> churches
) {

}


