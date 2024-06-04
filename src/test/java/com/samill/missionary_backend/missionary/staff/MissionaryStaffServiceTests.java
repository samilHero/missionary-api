package com.samill.missionary_backend.missionary.staff;


import com.samill.missionary_backend.common.AbstractSpringBootTests;
import com.samill.missionary_backend.missionary.missionary.repository.MissionaryRepository;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaffRole;
import com.samill.missionary_backend.missionary.staff.repository.MissionaryStaffRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;


public class MissionaryStaffServiceTests extends AbstractSpringBootTests {

    @Autowired
    private MissionaryStaffRepository missionaryStaffRepository;

    @Autowired
    private MissionaryRepository missionaryRepository;

    @Test
    @Rollback(false)
    void 스태프_등록() throws Exception {

        missionaryStaffRepository.save(MissionaryStaff.builder()
            .missionary(missionaryRepository.findById("c50bd2bb-69af-4560-a220-cd2fdf409336").orElseThrow())
            .userId("8ff1051f-085c-42ee-bacc-06656c9bf8db")
            .role(MissionaryStaffRole.MEMBER)

            .build()
        );
    }

}
