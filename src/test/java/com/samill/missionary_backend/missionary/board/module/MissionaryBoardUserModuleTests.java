//package com.samill.missionary_backend.missionary.board.module;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.samill.missionary_backend.common.AbstractControllerTest;
//import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
//import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
//import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
//import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
//import com.samill.missionary_backend.missionary.exception.MissionaryException;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//class MissionaryBoardUserModuleTests extends AbstractControllerTest {
//
//    private final String missionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409336";
//    private final String nonExistMissionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409337";
//    private final String missionaryBoardId = "44b38291-81e6-498d-83dc-701a73696036";
//
//    private final String memberId = "3068d98f-891b-4db0-9692-13de14f64f88";
//
//    @Autowired
//    private MissionaryBoardModuleMapFactory missionaryBoardModuleMapFactory;
//    private MissionaryBoardModule missionaryBoardModule;
//
//    @BeforeEach
//    void setUp() {
//        missionaryBoardModule = missionaryBoardModuleMapFactory.getMissionaryBoardModule(
//            MissionaryBoardUserModule.class
//        );
//    }
//
//
//    @Test
//    void 참가_선교_게시글_조회() throws MissionaryException {
//        final var missionaryBoard = missionaryBoardModule.getMissionaryBoard(memberId, missionaryBoardId);
//
//        assertThat(missionaryBoard).isNotNull();
//    }
//
//    @Test
//    void 참가_선교_게시글_목록_조회() throws MissionaryException {
//        final var missionaryBoards = missionaryBoardModule.getMissionaryBoards(
//            memberId,
//            new GetMissionaryBoardsQuery(
//                missionaryId,
//                MissionaryBoardType.NOTICE,
//                0,
//                10
//            )
//        ).getContent();
//
//        assertThat(missionaryBoards).isNotEmpty();
//    }
//
//    @Test
//    void 선교_게시글_작성() throws MissionaryException {
//        final var missionaryBoardId = missionaryBoardModule.createMissionaryBoard(
//            memberId,
//            new CreateMissionaryBoardCommand(
//                missionaryId,
//                "제목",
//                "내용",
//                MissionaryBoardType.NOTICE,
//                List.of()
//            )
//        );
//
//        final var getMissionaryBoardResult = missionaryBoardModule.getMissionaryBoard(memberId, missionaryBoardId);
//
//        assertThat(getMissionaryBoardResult).isNotNull();
//    }
//
//
//    @Test
//    void 선교_게시글_수정() throws MissionaryException {
//        missionaryBoardModule.updateBoard(
//            memberId,
//            new UpdateMissionaryBoardCommand(
//                missionaryBoardId,
//                "수정된 제목",
//                "수정된 내용",
//                List.of()
//            )
//        );
//
//        final var missionaryBoard = missionaryBoardModule.getMissionaryBoard(memberId, missionaryBoardId);
//
//        assertThat(missionaryBoard.getTitle()).isEqualTo("수정된 제목");
//        assertThat(missionaryBoard.getContent()).isEqualTo("수정된 내용");
//        assertThat(missionaryBoard.getFilesCount()).isEqualTo(0);
//    }
//
//}