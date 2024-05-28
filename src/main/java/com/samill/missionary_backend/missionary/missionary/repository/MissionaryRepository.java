package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionaryRepository extends JpaRepository<Missionary, String> {


}
