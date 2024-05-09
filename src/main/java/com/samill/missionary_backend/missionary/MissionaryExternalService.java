package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryCommand;
import java.util.List;
import lombok.NonNull;

public interface MissionaryExternalService {

    CreateMissionaryCommandResult createMissionary(@NonNull CreateMissionaryCommand createMissionaryCommand) throws CommonException;

    void updateMissionary(@NonNull String missionaryId, @NonNull UpdateMissionaryCommand updateMissionaryCommand) throws CommonException;

    void deleteMissionary(@NonNull String missionaryId);

    GetMissionaryQueryResult getMissionary(@NonNull GetMissionaryQuery getMissionaryQuery) throws CommonException;

    void getMissionaries(String cursor);

    void setMissionaryStaffs(
        @NonNull String missionaryId,
        @NonNull List<String> userIds
    );

//    void writeMissionaryBoard(
//        @NonNull String memberId,
//        @NonNull WriteMissionaryBoardDto writeMissionaryBoardDto
//    );
//
//    MissionaryBoardDto getMissionaryBoard(
//        @NonNull String memberId,
//        @NonNull String missionaryBoardId
//    );
//
//    void getMissionaryBoards(
//        @NonNull String memberId,
//        @NonNull String missionaryId,
//        String cursor
//    );

//    void updateMissionaryBoard(
//        @NonNull String memberId,
//        @NonNull String missionaryBoardId,
//        @NonNull UpdateMissionaryBoardDto updateMissionaryBoardDto
//    );
//
//    void deleteMissionaryBoard(
//        @NonNull String memberId,
//        @NonNull String missionaryBoardId
//    );
//

}
