package com.samill.missionary_backend.church.church.repository;

import com.samill.missionary_backend.church.church.dto.GetChurchesQueryCursor;
import com.samill.missionary_backend.church.church.entity.Church;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ChurchRepositoryCustom {

    Slice<Church> findAllWithCursor(GetChurchesQueryCursor cursor, @NonNull Pageable pageable);
}
