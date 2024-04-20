package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.missionary.board.dto.UpdateMissionaryBoardDto;
import com.samill.missionary_backend.missionary.board.dto.WriteMissionaryBoardDto;
import com.samill.missionary_backend.missionary.dto.MissionaryBoardDto;
import com.samill.missionary_backend.missionary.dto.MissionaryDto;
import com.samill.missionary_backend.missionary.missionary.dto.CreateMissionaryDto;
import java.util.List;
import lombok.NonNull;

public interface MissionaryExternalService {

    void createMissionary(
        @NonNull String adminId,
        @NonNull CreateMissionaryDto createMissionaryDto
    );

    void updateMissionary(
        @NonNull String adminId,
        @NonNull String missionaryId
    );

    void deleteMissionary(
        @NonNull String adminId,
        @NonNull String missionaryId
    );

    MissionaryDto getMissionary(
        @NonNull String memberId,
        @NonNull String missionaryId
    );

    List<MissionaryDto> getMissionaries(
        @NonNull String memberId,
        String cursor
    );

    void setMissionaryStaffs(
        @NonNull String adminId,
        @NonNull String missionaryId,
        @NonNull List<String> staffIds
    );


    void writeMissionaryBoard(
        @NonNull String memberId,
        @NonNull WriteMissionaryBoardDto writeMissionaryBoardDto
    );

    MissionaryBoardDto getMissionaryBoard(
        @NonNull String missionaryBoardId
    );

    void getMissionaryBoards(
        @NonNull String missionaryId,
        String cursor
    );

    void updateMissionaryBoard(
        @NonNull String memberId,
        @NonNull String missionaryBoardId,
        @NonNull UpdateMissionaryBoardDto updateMissionaryBoardDto
    );

    void deleteMissionaryBoard(
        @NonNull String memberId,
        @NonNull String missionaryBoardId
    );

    void createSchedule();

    void getSchedule();

    void updateSchedule();

    void deleteSchedule();


}
