package com.samill.missionary_backend.missionary.board.module;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.samill.missionary_backend.common.AbstractControllerTest;
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
class MissionaryBoardUserModuleTests extends AbstractControllerTest {

    private final String missionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409336";
    private final String nonExistMissionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409337";
    private final String missionaryBoardId = "44b38291-81e6-498d-83dc-701a73696036";
    private final String memberId = "3068d98f-891b-4db0-9692-13de14f64f88";
    private final String notParticipatingMissionaryId = "5c9e3daa-792f-40ea-b7bf-cb9853f6c1e1";

    @Autowired
    private MissionaryBoardModuleMapFactory missionaryBoardModuleMapFactory;
    private MissionaryBoardModule missionaryBoardModule;

    @BeforeEach
    void setUp() {
        missionaryBoardModule = missionaryBoardModuleMapFactory.getMissionaryBoardModule(
            MissionaryBoardUserModule.class
        );
    }


    @Test
    void 참가_선교_게시글_조회() throws CommonException {
        final var missionaryBoard = missionaryBoardModule.getMissionaryBoard(memberId, missionaryBoardId);

        assertThat(missionaryBoard).isNotNull();
    }

    /// TODO: 진족쓰 참가 신청 여부 작업 되면 작성 필요
    @Test
    void 미참가_선교_게시글_조회() {
//        assertThatThrownBy(
//            () -> missionaryBoardModule.getMissionaryBoard(memberId, notParticipatingMissionaryId)
//        ).isInstanceOf(AccessDeniedMissionaryBoardException.class);
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

    /// TODO: 진족쓰 참가 신청 여부 작업 되면 작성 필요
    @Test
    void 미참가_선교_게시글_목록_조회() {
//        assertThatThrownBy(
//            () -> missionaryBoardModule.getMissionaryBoards(
//                memberId,
//                new GetMissionaryBoardsQuery(
//                    notParticipatingMissionaryId,
//                    MissionaryBoardType.NOTICE,
//                    0,
//                    10
//                )
//            )
//        ).isInstanceOf(AccessDeniedMissionaryBoardException.class);
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
