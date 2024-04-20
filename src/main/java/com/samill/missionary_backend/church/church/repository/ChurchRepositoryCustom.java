package com.samill.missionary_backend.church.church.repository;

import com.samill.missionary_backend.church.church.dto.GetChurchesCursor;
import com.samill.missionary_backend.church.church.entity.Church;
import lombok.NonNull;

import java.util.List;

public interface ChurchRepositoryCustom {

    List<Church> findAllWithCursor(GetChurchesCursor cursor, @NonNull Integer pageSize);
}
