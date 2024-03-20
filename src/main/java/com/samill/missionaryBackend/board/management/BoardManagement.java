package com.samill.missionaryBackend.board.management;

import com.samill.missionaryBackend.board.BoardExternalAPI;
import com.samill.missionaryBackend.board.BoardInternalAPI;
import com.samill.missionaryBackend.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class BoardManagement implements BoardInternalAPI, BoardExternalAPI {

    private BoardService boardService;


}