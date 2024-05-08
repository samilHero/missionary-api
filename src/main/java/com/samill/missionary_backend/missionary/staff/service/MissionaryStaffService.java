package com.samill.missionary_backend.missionary.staff.service;


import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.staff.MissionaryStaffRepository;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryStaffService {

    private final MissionaryStaffRepository missionaryStaffRepository;

    public MissionaryStaff getMissionaryStaffById(String id) throws CommonException {
        return missionaryStaffRepository.findById(id)
            .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_MISSIONARY_STAFF));
    }

    public MissionaryStaff getMissionaryStaffByMissionaryIdUserId(String missionaryId, String userId) throws CommonException {
        return missionaryStaffRepository.findByMissionary_IdAndUserId(missionaryId, userId)
            .orElseThrow(() -> new CommonException(ResponseCode.NOT_FOUND_MISSIONARY_STAFF));
    }

}
