package com.samill.missionary_backend.missionary.mapper;

import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoardFile;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoardFiles;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryBoardCommandMissionaryBoardFile;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQueryResultMissionaryBoard;
import com.samill.missionary_backend.missionary.dto.GetMissionaryBoardsQueryResultMissionaryBoardFile;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryBoardCommandMissionaryBoardFile;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionaryBoardMapper {

    MissionaryBoardMapper INSTANCE = Mappers.getMapper(MissionaryBoardMapper.class);


    MissionaryBoard createMissionaryBoardCommandToMissionaryBoard(CreateMissionaryBoardCommand command);


    default MissionaryBoardFiles createMissionaryBoardCommandFilesToMissionaryBoardFiles(
        List<CreateMissionaryBoardCommandMissionaryBoardFile> files
    ) {
        return new MissionaryBoardFiles(
            files.stream()
                .map(this::createMissionaryBoardCommandFileToMissionaryBoardFile)
                .toList()
        );
    }


    MissionaryBoardFile createMissionaryBoardCommandFileToMissionaryBoardFile(
        CreateMissionaryBoardCommandMissionaryBoardFile createMissionaryBoardCommandMissionaryBoardFile
    );


    default MissionaryBoardFiles updateMissionaryBoardCommandFilesToMissionaryBoardFile(
        List<UpdateMissionaryBoardCommandMissionaryBoardFile> files
    ) {
        return new MissionaryBoardFiles(
            files.stream()
                .map(this::updateMissionaryBoardCommandFileToMissionaryBoardFile)
                .toList()
        );
    }


    MissionaryBoardFile updateMissionaryBoardCommandFileToMissionaryBoardFile(
        UpdateMissionaryBoardCommandMissionaryBoardFile updateMissionaryBoardCommandMissionaryBoardFile
    );


    @Mapping(target = "boards", source = "content", qualifiedByName = "missionaryBoardToGetMissionaryBoardsQueryResultMissionaryBoard")
    @Mapping(target = "totalPageCount", source = "totalPages")
    @Mapping(target = "totalCount", source = "totalElements")
    GetMissionaryBoardsQueryResult missionaryBoardsToGetMissionaryBoardsQueryResult(Page<MissionaryBoard> missionaryBoards);

    @Named("missionaryBoardToGetMissionaryBoardsQueryResultMissionaryBoard")
    @Mapping(target = "files", source = "files.files", qualifiedByName = "missionaryBoardFileToGetMissionaryBoardsQueryResultMissionaryBoardFile")
    GetMissionaryBoardsQueryResultMissionaryBoard missionaryBoardToGetMissionaryBoardsQueryResultMissionaryBoard(MissionaryBoard missionaryBoard);


    @Named("missionaryBoardFileToGetMissionaryBoardsQueryResultMissionaryBoardFile")
    GetMissionaryBoardsQueryResultMissionaryBoardFile missionaryBoardFileToGetMissionaryBoardsQueryResultMissionaryBoardFile(
        MissionaryBoardFile missionaryBoardFile
    );

}

