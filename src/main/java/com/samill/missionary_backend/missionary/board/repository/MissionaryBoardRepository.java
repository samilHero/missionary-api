package com.samill.missionary_backend.missionary.board.repository;

import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionaryBoardRepository extends JpaRepository<MissionaryBoard, String> {

    Page<MissionaryBoard> findAllByMissionary_Id(String missionaryId, Pageable pageable);
}
