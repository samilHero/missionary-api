package com.samill.missionary_backend.missionary.staff.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import com.samill.missionary_backend.missionary.staff.enums.MissionaryStaffRole;
import com.samill.missionary_backend.missionary.staff.repository.MissionaryStaffRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({JpaConfig.class, DateTimeProviderConfig.class})
class MissionaryStaffServiceTests {


    @InjectMocks
    private MissionaryStaffService missionaryStaffService;

    @Mock
    private MissionaryStaffRepository missionaryStaffRepository;


    @Test
    void 선교_스태프_존재_여부() {
        /// Given
        final var missionaryId = "db6d5fb0-2b68-47cd-ba6d-653276c23efb";
        final var userId = "8ff1051f-085c-42ee-bacc-06656c9bf8db";
        when(missionaryStaffRepository.findByMissionary_IdAndUserId(missionaryId, userId)).thenReturn(Optional.of(mock(MissionaryStaff.class)));

        /// When
        final var isExisted = missionaryStaffService.isExistedMissionaryStaff(missionaryId, userId);

        /// Then
        assertThat(isExisted).isTrue();
    }

    @Test
    void 선교_스태프_임명() {
        /// Given
        final var missionary = Missionary.builder()
            .id("db6d5fb0-2b68-47cd-ba6d-653276c23efb")
            .name("선교")
            .build();

        final var userId = "8ff1051f-085c-42ee-bacc-06656c9bf8db";

        when(missionaryStaffRepository.findAllByUserId(anyString())).thenReturn(
            List.of(MissionaryStaff.builder().missionary(missionary).userId(userId).build())
        );

        missionaryStaffService.appointMissionaryStaffs(
            missionary,
            List.of(new AppointMissionaryStaffsCommandStaff(userId, MissionaryStaffRole.MEMBER))
        );

        assertThat(missionaryStaffService.getMissionaryStaffsByUserId(userId))
            .anyMatch(missionaryStaff -> missionaryStaff.getUserId().equals(userId));
    }

    @Test
    void 선교_스태프_해임() throws MissionaryException {
        /// Given
        final var missionaryId = "db6d5fb0-2b68-47cd-ba6d-653276c23efb";
        final var userId = "8ff1051f-085c-42ee-bacc-06656c9bf8db";
        when(missionaryStaffRepository.existsByMissionary_IdAndUserId(anyString(), anyString())).thenReturn(true);

        /// When
        missionaryStaffService.disappointMissionaryStaffs(
            missionaryId,
            List.of(userId)
        );

        /// Then
        verify(missionaryStaffRepository, times(1)).deleteAllByMissionary_IdAndUserIdIn(missionaryId, List.of(userId));
    }
}
