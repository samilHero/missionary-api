package com.samill.missionary_backend.missionary.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.samill.missionary_backend.common.AbstractSpringBootTests;
import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import com.samill.missionary_backend.missionary.board.exception.NotFoundMissionaryBoardException;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class MissionaryServiceTests extends AbstractSpringBootTests {

    private final String missionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409336";

    @Autowired
    private MissionaryBoardService missionaryBoardService;

    @Autowired
    private MissionaryService missionaryService;

    @Test
    @Transactional
    void 선교_게시글_목록_조회() {
        final var missionaryBoards = missionaryBoardService.getMissionaryBoards(
            new GetMissionaryBoardsQuery(
                missionaryId,
                MissionaryBoardType.NOTICE,
                0,
                10
            )
        ).getContent();

        assertThat(missionaryBoards).isNotEmpty();
    }

    @Test
    @Transactional
    void 선교_게시글_작성() throws MissionaryException {
        final var missionaryBoard = missionaryBoardService.createMissionaryBoard(
            missionaryService.getMissionary(missionaryId),
            new CreateMissionaryBoardCommand(
                missionaryId,
                "제목",
                "내용",
                MissionaryBoardType.NOTICE,
                List.of()
            )
        );

        final var getMissionaryBoardResult = missionaryBoardService.getMissionaryBoard(missionaryBoard.getId());

        assertThat(getMissionaryBoardResult).isNotNull();
        assertThat(getMissionaryBoardResult.getTitle()).isEqualTo(missionaryBoard.getTitle());
        assertThat(getMissionaryBoardResult.getContent()).isEqualTo(missionaryBoard.getContent());
        assertThat(getMissionaryBoardResult.getType()).isEqualTo(missionaryBoard.getType());
        assertThat(getMissionaryBoardResult.getMissionaryId()).isEqualTo(missionaryBoard.getMissionaryId());
        assertThat(getMissionaryBoardResult.getFilesCount()).isEqualTo(missionaryBoard.getFilesCount());
    }

    @Test
    @Transactional
    void 선교_게시글_수정() throws MissionaryException {

        final var updateMissionaryBoardCommand = new UpdateMissionaryBoardCommand(
            "44b38291-81e6-498d-83dc-701a73696036",
            "수정된 제목",
            "수정된 내용",
            List.of()
        );

        missionaryBoardService.updateMissionaryBoard(updateMissionaryBoardCommand);

        final var missionaryBoard = missionaryBoardService.getMissionaryBoard(updateMissionaryBoardCommand.id());

        assertThat(missionaryBoard.getTitle()).isEqualTo(updateMissionaryBoardCommand.title());
        assertThat(missionaryBoard.getContent()).isEqualTo(updateMissionaryBoardCommand.content());
        assertThat(missionaryBoard.getFilesCount()).isEqualTo(updateMissionaryBoardCommand.files().size());
    }

    @Test
    @Transactional
    void 선교_게시글_삭제() throws MissionaryException {
        final var missionaryBoardId = "44b38291-81e6-498d-83dc-701a73696036";

        missionaryBoardService.deleteMissionaryBoard(new DeleteMissionaryBoardCommand(missionaryBoardId));

        assertThatThrownBy(() -> missionaryBoardService.getMissionaryBoard(missionaryBoardId)).isInstanceOf(NotFoundMissionaryBoardException.class);


    }
}
