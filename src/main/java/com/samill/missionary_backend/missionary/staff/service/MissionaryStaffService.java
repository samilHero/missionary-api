package com.samill.missionary_backend.missionary.staff.service;


import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.mapper.MissionaryStaffMapper;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import com.samill.missionary_backend.missionary.staff.repository.MissionaryStaffRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryStaffService {

    private final MissionaryStaffRepository missionaryStaffRepository;

    public Boolean isExistedMissionaryStaff(String missionaryId, String userId) {
        return missionaryStaffRepository.findByMissionary_IdAndUserId(missionaryId, userId).isPresent();
    }

    public void appointMissionaryStaffs(
        @NonNull Missionary missionary,
        @NonNull List<AppointMissionaryStaffsCommandStaff> staffs
    ) {
        missionaryStaffRepository.saveAll(
            staffs.stream()
                .map((staff) -> MissionaryStaffMapper.INSTANCE.appointMissionaryStaffToMissionaryStaff(missionary, staff))
                .toList()
        );
    }

    public void disappointMissionaryStaffs(@NonNull String missionaryId, @NonNull List<String> userIds) {
        missionaryStaffRepository.deleteAllByMissionary_IdAndUserIdIn(missionaryId, userIds);
    }

    public List<MissionaryStaff> getMissionaryStaffsByUserId(String userId) {
        return missionaryStaffRepository.findAllByUserId(userId);
    }

}
