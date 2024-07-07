package com.samill.missionary_backend.missionary;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.member.dto.GetMemberServiceTypeDto;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.member.enums.ServiceType;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.enums.MissionaryStaffRole;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import com.samill.missionary_backend.missionary.staff.service.MissionaryStaffService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MissionaryMockManagementTests {

    @InjectMocks
    private MissionaryManagement missionaryManagement;

    @Mock
    private MemberExternalService memberExternalService;

    @Mock
    private MissionaryService missionaryService;

    @Mock
    private MissionaryStaffService missionaryStaffService;

    @Test
    void 선교_준비팀_임명() throws CommonException {
        // Given
        final var serviceType = mock(ServiceType.class);
        when(serviceType.isNotAdmin()).thenReturn(false);

        final var getMemberServiceTypeDto = mock(GetMemberServiceTypeDto.class);
        when(getMemberServiceTypeDto.serviceType()).thenReturn(serviceType);

        when(memberExternalService.getMemberServiceType(anyString()))
            .thenReturn(getMemberServiceTypeDto);

        final var getUserDto = mock(GetUserDto.class);
        when(getUserDto.id()).thenReturn("missionary_staff_member_id");
        when(memberExternalService.getUsersByIds(any(List.class)))
            .thenReturn(List.of(getUserDto));

        final var missionary = mock(Missionary.class);
        when(missionaryService.getMissionary(anyString())).thenReturn(missionary);

        final var missionaryStaff = MissionaryStaff.builder()
            .id("missionary_staff_id")
            .userId("missionary_staff_member_id")
            .missionary(missionary)
            .role(MissionaryStaffRole.MEMBER)
            .build();

        when(missionaryStaffService.appointMissionaryStaffs(any(Missionary.class), any(List.class)))
            .thenReturn(List.of(missionaryStaff));

        final var command = new AppointMissionaryStaffsCommand(
            "missionaryId",
            List.of(
                new AppointMissionaryStaffsCommandStaff(
                    missionaryStaff.getUserId(),
                    missionaryStaff.getRole().getKey()
                )
            )
        );

        // When
        final var result = missionaryManagement.appointMissionaryStaffs(
            "admin",
            command
        );

        // Then

        assertThat(result.missionaryStaffs()).hasSize(1);
        assertThat(result.missionaryStaffs().get(0).id()).isEqualTo("missionary_staff_id");
        assertThat(result.missionaryStaffs().get(0).userId()).isEqualTo("missionary_staff_member_id");
        assertThat(result.missionaryStaffs().get(0).role()).isEqualTo(MissionaryStaffRole.MEMBER.getKey());

        verify(memberExternalService, times(1)).getMemberServiceType("admin");
        verify(memberExternalService, times(1)).getUsersByIds(List.of("missionary_staff_member_id"));
        verify(missionaryService, times(1)).getMissionary("missionaryId");
        verify(missionaryStaffService, times(1)).appointMissionaryStaffs(missionary, command.staffs());
        verifyNoMoreInteractions(memberExternalService, missionaryService, missionaryStaffService);
    }
}
