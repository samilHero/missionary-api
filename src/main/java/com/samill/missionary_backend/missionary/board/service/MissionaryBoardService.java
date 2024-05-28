package com.samill.missionary_backend.missionary.board.service;

import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.exception.MissionaryBoardException;
import com.samill.missionary_backend.missionary.board.exception.NotFoundMissionaryBoardException;
import com.samill.missionary_backend.missionary.board.repository.MissionaryBoardRepository;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.DeleteMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.mapper.MissionaryBoardMapper;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryBoardService {

    private final MissionaryBoardRepository missionaryBoardRepository;

    public MissionaryBoard createMissionaryBoard(
        @NonNull Missionary missionary,
        @NonNull CreateMissionaryBoardCommand command
    ) {
        final MissionaryBoard missionaryBoard = MissionaryBoardMapper.INSTANCE.createMissionaryBoardCommandToMissionaryBoard(command);
        missionaryBoard.linkMissionary(missionary);

        return missionaryBoardRepository.save(missionaryBoard);
    }

    public MissionaryBoard getMissionaryBoard(String missionaryBoard) throws NotFoundMissionaryBoardException {
        return missionaryBoardRepository.findById(missionaryBoard).orElseThrow(NotFoundMissionaryBoardException::new);
    }


    public Page<MissionaryBoard> getMissionaryBoards(@NonNull GetMissionaryBoardsQuery query) {
        return missionaryBoardRepository.findAllByMissionary_Id(
            query.missionaryId(),
            PageRequest.of(
                query.pageNumber(),
                query.pageSize(),
                Sort.by(Direction.DESC, "createdAt")
            )
        );
    }


    public void updateMissionaryBoard(@NonNull UpdateMissionaryBoardCommand command) throws MissionaryBoardException {
        final MissionaryBoard missionaryBoard = missionaryBoardRepository.findById(command.id())
            .orElseThrow(NotFoundMissionaryBoardException::new);

        missionaryBoard.changeTitle(command.title());
        missionaryBoard.changeContent(command.content());
        missionaryBoard.changeFiles(
            command.keepFileIds(),
            MissionaryBoardMapper.INSTANCE.updateMissionaryBoardCommandFilesToMissionaryBoardFile(command.newFiles())
        );

    }

    public void deleteMissionaryBoard(@NonNull DeleteMissionaryBoardCommand command) {
        missionaryBoardRepository.deleteById(command.missionaryBoardId());
    }
}
