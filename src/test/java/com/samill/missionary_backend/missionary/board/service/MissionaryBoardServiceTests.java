package com.samill.missionary_backend.missionary.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoardFiles;
import com.samill.missionary_backend.missionary.board.enums.MissionaryBoardType;
import com.samill.missionary_backend.missionary.board.exception.NotFoundMissionaryBoardException;
import com.samill.missionary_backend.missionary.board.repository.MissionaryBoardRepository;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({JpaConfig.class, DateTimeProviderConfig.class})
class MissionaryBoardServiceTests {

    private final String missionaryId = "c50bd2bb-69af-4560-a220-cd2fdf409336";

    @InjectMocks
    private MissionaryBoardService missionaryBoardService;

    @Mock
    private MissionaryBoardRepository missionaryBoardRepository;

    @Test
    void 선교_게시글_목록_조회() {
        final var page = mock(PageImpl.class);
        when(page.getContent()).thenReturn(List.of(mock(MissionaryBoard.class)));
        when(missionaryBoardRepository.findAllByMissionary_Id(anyString(), any(PageRequest.class)))
            .thenReturn(page);

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
    void 선교_게시글_작성() throws MissionaryException {
        // Given
        final var missionary = mock(Missionary.class);
        when(missionary.getId()).thenReturn(missionaryId);

        final var missionaryBoard = mock(MissionaryBoard.class);
        when(missionaryBoard.getId()).thenReturn(UUID.randomUUID().toString());
        when(missionaryBoardRepository.save(any(MissionaryBoard.class)))
            .thenReturn(missionaryBoard);

        // When
        final var savedMissionaryBoard = missionaryBoardService.createMissionaryBoard(
            missionary,
            new CreateMissionaryBoardCommand(
                missionaryId,
                "제목",
                "내용",
                MissionaryBoardType.NOTICE,
                List.of()
            )
        );

        assertThat(savedMissionaryBoard.getId()).isEqualTo(missionaryBoard.getId());
    }

    //
    @Test
    void 선교_게시글_수정() throws MissionaryException {
        final var missionaryBoard = MissionaryBoard.builder()
            .id("44b38291-81e6-498d-83dc-701a73696036")
            .title("수정전 제목")
            .content("수정전 내용")
            .files(MissionaryBoardFiles.empty())
            .build();

        when(missionaryBoardRepository.findById(anyString())).thenReturn(Optional.of(missionaryBoard));

        final var updateMissionaryBoardCommand = new UpdateMissionaryBoardCommand(
            "44b38291-81e6-498d-83dc-701a73696036",
            "수정된 제목",
            "수정된 내용",
            List.of()
        );

        missionaryBoardService.updateMissionaryBoard(updateMissionaryBoardCommand);

        assertThat(missionaryBoard.getId()).isEqualTo(updateMissionaryBoardCommand.id());
        assertThat(missionaryBoard.getContent()).isEqualTo(updateMissionaryBoardCommand.content());
        assertThat(missionaryBoard.getTitle()).isEqualTo(updateMissionaryBoardCommand.title());
        assertThat(missionaryBoard.getFilesCount()).isEqualTo(updateMissionaryBoardCommand.files().size());
    }

    @Test
    void 선교_게시글_수정_실패() {
        when(missionaryBoardRepository.findById(anyString())).thenReturn(Optional.empty());
        final var updateMissionaryBoardCommand = new UpdateMissionaryBoardCommand(
            "44b38291-81e6-498d-83dc-701a73696036",
            "수정된 제목",
            "수정된 내용",
            List.of()
        );

        assertThatThrownBy(() -> missionaryBoardService.updateMissionaryBoard(updateMissionaryBoardCommand))
            .isInstanceOf(NotFoundMissionaryBoardException.class);
    }

    @Test
    void 선교_게시글_삭제() throws MissionaryException {
        // Given

        // When
        missionaryBoardService.deleteMissionaryBoard(new DeleteMissionaryBoardCommand(missionaryId));

        // Then
        verify(missionaryBoardRepository, times(1)).deleteById(missionaryId);
    }

}
