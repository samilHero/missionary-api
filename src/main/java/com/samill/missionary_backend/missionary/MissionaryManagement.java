package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.missionary.dto.*;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import com.samill.missionary_backend.missionary.staff.service.MissionaryStaffService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class MissionaryManagement implements MissionaryExternalService {

    private final MissionaryService missionaryService;
    private final MissionaryStaffService missionaryStaffService;
    private final MemberExternalService memberExternalService;

    @Override
    @Transactional
    public CreateMissionaryCommandResult createMissionary(@NonNull CreateMissionaryCommand createMissionaryCommand) throws CommonException {
        return missionaryService.createMissionary(createMissionaryCommand);
    }

    @Override
    @Transactional
    public void updateMissionary(@NonNull String missionaryId, @NonNull UpdateMissionaryCommand updateMissionaryCommand) throws CommonException {
        missionaryService.updateMissionary(missionaryId, updateMissionaryCommand);
    }

    @Override
    public void deleteMissionary(@NonNull String missionaryId) {
        missionaryService.deleteMissionary(missionaryId);
    }

    @Override
    public GetMissionaryQueryResult getMissionary(@NonNull GetMissionaryQuery getMissionaryQuery) throws CommonException {
        return missionaryService.getMissionary(getMissionaryQuery);
    }

    @Override
    public void getMissionaries(String cursor) {
        missionaryService.getMissionaries(cursor);
    }

    @Override
    public void setMissionaryStaffs(
        @NonNull String missionaryId,
        @NonNull List<String> staffIds
    ) {

    }

    @Override
    public boolean isInParticipationPeriod(@NonNull String missionaryId) {
        // 선교 신청 가능 기간인지?
        return true;
    }

    @Override
    public List<String> getDaysBeforeMissionaryIds(GetMissionaryIdsQuery getMissionaryIdsQuery) {
        // 선교 종료일이 특정 date 보다 작거나 같은 선교 ID List 주세요
        return null;
    }


}
