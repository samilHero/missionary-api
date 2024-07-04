package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import java.util.List;

public interface MissionaryRepositoryCustom {

    List<Missionary> findLatestMissionariesByRegion();


    List<Missionary> findLatestMissionariesByRegion(String userId);

}
