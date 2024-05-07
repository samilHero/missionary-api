package com.samill.missionary_backend.missionary.staff.service;


import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.staff.MissionaryStaffRepository;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryStaffService {

    private final MissionaryStaffRepository missionaryStaffRepository;

    public MissionaryStaff getMissionaryStaff(String id) throws CommonException {

        return this.missionaryStaffRepository.findById(id).orElseThrow(CommonException::new);
    }

    public MissionaryStaff getMissionaryStaff(String missionary, String userId) throws CommonException {
        return this.missionaryStaffRepository.findByMissionary_IdAndUserId(missionary, userId).orElseThrow(CommonException::new);
    }

}
