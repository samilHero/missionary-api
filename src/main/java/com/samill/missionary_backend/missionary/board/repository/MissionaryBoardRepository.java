package com.samill.missionary_backend.missionary.board.repository;

import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionaryBoardRepository extends JpaRepository<MissionaryBoard, String> {

}
