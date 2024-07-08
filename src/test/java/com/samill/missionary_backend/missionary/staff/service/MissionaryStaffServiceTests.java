package com.samill.missionary_backend.missionary.staff.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.enums.MissionaryStaffRole;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import com.samill.missionary_backend.missionary.staff.repository.MissionaryStaffRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
            .name("선교")
            .build();

        final var userId = "8ff1051f-085c-42ee-bacc-06656c9bf8db";

        final var missionaryStaff = MissionaryStaff.builder()
            .missionary(missionary)
            .userId(userId)
            .build();

        final var savedMissionaryStaff = MissionaryStaff.builder()
            .id(UUID.randomUUID().toString())
            .missionary(missionary)
            .userId(userId)
            .build();

        when(missionaryStaffRepository.saveAll(List.of(missionaryStaff))).thenReturn(List.of(savedMissionaryStaff));

        // When
        final var missionaryStaffs = missionaryStaffService.appointMissionaryStaffs(
            missionary,
            List.of(new AppointMissionaryStaffsCommandStaff(userId, MissionaryStaffRole.MEMBER.getKey()))
        );

        // Then
        assertThat(missionaryStaffs).contains(savedMissionaryStaff);
        assertThat(missionaryStaffs).hasSize(1);
        verify(missionaryStaffRepository, times(1)).saveAll(List.of(missionaryStaff));
        verifyNoMoreInteractions(missionaryStaffRepository);
    }

    @Test
    void 선교_스태프_해임() throws MissionaryException {
        /// Given
        final var missionaryId = "db6d5fb0-2b68-47cd-ba6d-653276c23efb";
        final var userIds = List.of("8ff1051f-085c-42ee-bacc-06656c9bf8db");

        /// When
        missionaryStaffService.disappointMissionaryStaffs(
            missionaryId,
            userIds
        );

        /// Then
        verify(missionaryStaffRepository, times(1)).deleteAllByMissionary_IdAndUserIdIn(missionaryId, userIds);
        verifyNoMoreInteractions(missionaryStaffRepository);
    }
}
