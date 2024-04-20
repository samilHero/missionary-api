package com.samill.missionary_backend.church.repository;

import com.samill.missionary_backend.church.entity.Church;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChurchRepository extends JpaRepository<Church, String>, ChurchRepositoryCustom {


}
