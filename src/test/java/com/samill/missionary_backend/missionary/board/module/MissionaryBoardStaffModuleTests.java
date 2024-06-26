package com.samill.missionary_backend.missionary.board.module;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.samill.missionary_backend.common.AbstractSpringBootTests;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class MissionaryBoardStaffModuleTests extends AbstractSpringBootTests {

    private final String missionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409336";
    private final String nonExistMissionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409337";
    private final String memberId = "07112ca9-1de0-49d9-809a-f12bb437148b";

    private final String missionaryBoardId = "44b38291-81e6-498d-83dc-701a73696036";
    private final String unPermittedMemberId = "ffa17f59-7e86-45eb-8400-14d29a56ca42";

    @Autowired
    private MissionaryBoardModuleMapFactory missionaryBoardModuleMapFactory;
    private MissionaryBoardModule missionaryBoardModule;

    @BeforeEach
    void setUp() {
        missionaryBoardModule = missionaryBoardModuleMapFactory.getMissionaryBoardModule(
            MissionaryBoardStaffModule.class
        );
    }


    @Test
    void 선교_게시글_목록_조회() throws CommonException {
        final var missionaryBoards = missionaryBoardModule.getMissionaryBoards(
            memberId,
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
    void 선교_게시글_작성() throws CommonException {
        final var missionaryBoardId = missionaryBoardModule.createMissionaryBoard(
            memberId,
            new CreateMissionaryBoardCommand(
                missionaryId,
                "제목",
                "내용",
                MissionaryBoardType.NOTICE,
                List.of()
            )
        );

        final var getMissionaryBoardResult = missionaryBoardModule.getMissionaryBoard(memberId, missionaryBoardId);

        assertThat(getMissionaryBoardResult).isNotNull();
    }

    @Test
    void 스태프_아닌_멤버의_게시글_작성() {
        assertThatThrownBy(() -> missionaryBoardModule.createMissionaryBoard(
            unPermittedMemberId,
            new CreateMissionaryBoardCommand(
                missionaryId,
                "제목",
                "내용",
                MissionaryBoardType.NOTICE,
                List.of()
            )
        )).isInstanceOf(MissionaryException.class);
    }


    @Test
    void 존재_하지_않는_선교의_게시글_작성() {
        assertThatThrownBy(() -> missionaryBoardModule.createMissionaryBoard(
            memberId,
            new CreateMissionaryBoardCommand(
                nonExistMissionaryId,
                "제목",
                "내용",
                MissionaryBoardType.NOTICE,
                List.of()
            )
        )).isInstanceOf(MissionaryException.class);
    }


    @Test
    void 선교_게시글_수정() throws CommonException {
        missionaryBoardModule.updateBoard(
            memberId,
            new UpdateMissionaryBoardCommand(
                missionaryBoardId,
                "수정된 제목",
                "수정된 내용",
                List.of()
            )
        );

        final var missionaryBoard = missionaryBoardModule.getMissionaryBoard(memberId, missionaryBoardId);

        assertThat(missionaryBoard.getTitle()).isEqualTo("수정된 제목");
        assertThat(missionaryBoard.getContent()).isEqualTo("수정된 내용");
        assertThat(missionaryBoard.getFilesCount()).isEqualTo(0);
    }

    @Test
    void 스태프_아닌_멤버의_게시글_수정() {

        assertThatThrownBy(() -> missionaryBoardModule.updateBoard(
            unPermittedMemberId,
            new UpdateMissionaryBoardCommand(
                missionaryBoardId,
                "수정된 제목",
                "수정된 내용",
                List.of()
            )
        )).isInstanceOf(MissionaryException.class);
    }

    @Test
    void 게시글_삭제() throws CommonException {
        missionaryBoardModule.deleteMissionaryBoard(memberId, new DeleteMissionaryBoardCommand(missionaryBoardId));
    }

    @Test
    void 스태프_아닌_멤버의_게시글_삭제() {
        assertThatThrownBy(() -> missionaryBoardModule.deleteMissionaryBoard(
            unPermittedMemberId,
            new DeleteMissionaryBoardCommand(missionaryBoardId)
        )).isInstanceOf(MissionaryException.class);
    }

}
