package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.missionary.board.dto.UpdateMissionaryBoardDto;
import com.samill.missionary_backend.missionary.board.dto.WriteMissionaryBoardDto;
import com.samill.missionary_backend.missionary.dto.MissionaryBoardDto;
import com.samill.missionary_backend.missionary.dto.MissionaryDto;
import com.samill.missionary_backend.missionary.missionary.dto.CreateMissionaryDto;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class MissionaryManagement implements MissionaryExternalService {
    @Override
    public void createMissionary(@NonNull String adminId, @NonNull CreateMissionaryDto createMissionaryDto) {

    }

    @Override
    public void updateMissionary(@NonNull String adminId, @NonNull String missionaryId) {

    }

    @Override
    public void deleteMissionary(@NonNull String adminId, @NonNull String missionaryId) {

    }

    @Override
    public MissionaryDto getMissionary(@NonNull String memberId, @NonNull String missionaryId) {
        return null;
    }

    @Override
    public List<MissionaryDto> getMissionaries(@NonNull String memberId, String cursor) {
        return null;
    }

    @Override
    public void setMissionaryStaffs(@NonNull String adminId, @NonNull String missionaryId, @NonNull List<String> staffIds) {

    }

    @Override
    public void writeMissionaryBoard(@NonNull String memberId, @NonNull WriteMissionaryBoardDto writeMissionaryBoardDto) {

    }

    @Override
    public MissionaryBoardDto getMissionaryBoard(@NonNull String missionaryBoardId) {
        return null;
    }

    @Override
    public void getMissionaryBoards(@NonNull String missionaryId, String cursor) {

    }

    @Override
    public void updateMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId, @NonNull UpdateMissionaryBoardDto updateMissionaryBoardDto) {

    }

    @Override
    public void deleteMissionaryBoard(@NonNull String memberId, @NonNull String missionaryBoardId) {

    }

    @Override
    public void createSchedule() {

    }

    @Override
    public void getSchedule() {

    }

    @Override
    public void updateSchedule() {

    }

    @Override
    public void deleteSchedule() {

    }
//
//    private MemberExternalService memberExternalService;
//    private ChurchExternalService churchExternalService;


}
