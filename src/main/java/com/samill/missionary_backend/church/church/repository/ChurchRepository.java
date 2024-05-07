package com.samill.missionary_backend.church.church.repository;

import com.samill.missionary_backend.church.church.entity.Church;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChurchRepository extends JpaRepository<Church, String>, ChurchRepositoryCustom {

    List<Church> findAllByOrderByNameAscIdAsc();

}
