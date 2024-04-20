package com.samill.missionary_backend.church.repository;

import com.samill.missionary_backend.church.entity.Church;
import java.util.List;

public interface ChurchRepositoryCustom {

    List<Church> findAllWithCursorWithPageSize(String cursor, Integer pageSize);
}
