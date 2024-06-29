package com.samill.missionary_backend.missionary.board.module;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.samill.missionary_backend.common.AbstractSpringBootTests;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import com.samill.missionary_backend.missionary.board.exception.AccessDeniedMissionaryBoardException;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class MissionaryBoardUserModuleTests extends AbstractSpringBootTests {

    private final String missionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409336";
    private final String nonExistMissionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409337";
    private final String missionaryBoardId = "44b38291-81e6-498d-83dc-701a73696036";

    private final String memberId = "07112ca9-1de0-49d9-809a-f12bb437148b";
    private final String notParticipatingMissionaryId = "5c9e3daa-792f-40ea-b7bf-cb9853f6c1e1";

    @Autowired
    private MissionaryBoardModuleMapFactory missionaryBoardModuleMapFactory;
    private MissionaryBoardUserModule missionaryBoardModule;

    @BeforeEach
    void setUp() {
        missionaryBoardModule = (MissionaryBoardUserModule) missionaryBoardModuleMapFactory.getMissionaryBoardModule(
            MissionaryBoardUserModule.class
        );
    }


    @Test
    void 참가_선교_게시글_조회() throws CommonException {
        final var missionaryBoard = missionaryBoardModule.getMissionaryBoard(memberId, missionaryBoardId);

        assertThat(missionaryBoard).isNotNull();
    }


    @Test
    void 미참가_선교_게시글_조회() {
        assertThatThrownBy(
            () -> missionaryBoardModule.getMissionaryBoard(memberId, "d76d9235-6d78-4076-b632-4b1ab38d197f")
        ).isInstanceOf(AccessDeniedMissionaryBoardException.class);
    }

    @Test
    void 참가_선교_게시글_목록_조회() throws CommonException {
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
    void 미참가_선교_게시글_목록_조회() {
        assertThatThrownBy(
            () -> missionaryBoardModule.getMissionaryBoards(
                memberId,
                new GetMissionaryBoardsQuery(
                    notParticipatingMissionaryId,
                    MissionaryBoardType.NOTICE,
                    0,
                    10
                )
            )
        ).isInstanceOf(AccessDeniedMissionaryBoardException.class);
    }

    @Test
    void 선교_게시글_작성() throws CommonException {
        assertThatThrownBy(
            () -> missionaryBoardModule.createMissionaryBoard(
                memberId,
                new CreateMissionaryBoardCommand(
                    missionaryId,
                    "제목",
                    "내용",
                    MissionaryBoardType.NOTICE,
                    List.of()
                )
            )
        ).isInstanceOf(AccessDeniedMissionaryBoardException.class);


    }


    @Test
    void 선교_게시글_수정() throws CommonException {
        assertThatThrownBy(
            () -> missionaryBoardModule.updateBoard(
                memberId,
                new UpdateMissionaryBoardCommand(
                    missionaryBoardId,
                    "수정된 제목",
                    "수정된 내용",
                    List.of()
                )
            )
        ).isInstanceOf(AccessDeniedMissionaryBoardException.class);
    }


}
