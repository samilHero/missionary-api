package com.samill.missionary_backend.missionary.church.repository;

import com.samill.missionary_backend.missionary.church.entity.MissionaryChurch;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MissionaryChurchRepositoryCustom {

    Slice<MissionaryChurch> findAllWithCursor(String missionaryId, @NonNull Pageable pageable);
}
