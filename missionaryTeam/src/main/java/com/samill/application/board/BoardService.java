package com.samill.application.board;

import java.util.List;

public interface BoardService {
    Board createTeamBoard(Board board);
    List<Board> getMissionaryTeamBoardList();
    Board getMissionaryTeamBoard(Long boardId);
    Board updateTeamBoard(Board board);
    void deleteTeamBoard(Long boardId);
}
