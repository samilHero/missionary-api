package com.samill.missionary_backend.missionary.staff.service;


import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import com.samill.missionary_backend.missionary.staff.repository.MissionaryStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryStaffService {

    private final MissionaryStaffRepository missionaryStaffRepository;

    public MissionaryStaff getMissionaryStaff(String id) throws MissionaryException {
        return missionaryStaffRepository.findById(id)
            .orElseThrow(() -> new MissionaryException(ResponseCode.NOT_FOUND_MISSIONARY_STAFF));
    }

    public MissionaryStaff getMissionaryStaff(String missionaryId, String userId) throws MissionaryException {
        return missionaryStaffRepository.findByMissionary_IdAndUserId(missionaryId, userId)
            .orElseThrow(() -> new MissionaryException(ResponseCode.NOT_FOUND_MISSIONARY_STAFF));
    }

    public Boolean isExistedMissionaryStaff(String missionaryId, String userId) {
        return missionaryStaffRepository.findByMissionary_IdAndUserId(missionaryId, userId).isPresent();
    }


}
