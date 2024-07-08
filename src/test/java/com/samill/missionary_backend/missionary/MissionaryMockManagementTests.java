package com.samill.missionary_backend.missionary;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.member.dto.GetMemberServiceTypeDto;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.member.member.enums.ServiceType;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.DisappointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQuery;
import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import com.samill.missionary_backend.missionary.enums.MissionaryStaffRole;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.service.MissionaryService;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import com.samill.missionary_backend.missionary.region.service.MissionaryRegionService;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import com.samill.missionary_backend.missionary.staff.exception.AccessDeniedMissionaryStaffException;
import com.samill.missionary_backend.missionary.staff.service.MissionaryStaffService;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;


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

    @Mock
    private MissionaryRegionService missionaryRegionService;

    @Test
    void 선교_지역_목록_조회() {
        final List<MissionaryRegion> domestics = List.of(
            MissionaryRegion.builder()
                .id("1")
                .name("국내")
                .type(MissionaryRegionType.DOMESTIC)
                .build()
        );

        final List<MissionaryRegion> abroads = List.of(
            MissionaryRegion.builder()
                .id("2")
                .name("해외")
                .type(MissionaryRegionType.ABROAD)
                .build()
        );

        final var regionsMap = Map.<MissionaryRegionType, List<MissionaryRegion>>of(
            MissionaryRegionType.DOMESTIC,
            domestics,
            MissionaryRegionType.ABROAD,
            abroads
        );

        when(missionaryRegionService.getMissionaryRegionTypeMissionaryRegionsMap())
            .thenReturn(regionsMap);

        final var result = missionaryManagement.getMissionaryRegions();

        assertThat(result).isNotNull();

        for (var resultRegion : result.domestic()) {
            regionsMap.get(MissionaryRegionType.DOMESTIC).stream()
                .filter(region -> region.getId().equals(resultRegion.id()))
                .findFirst()
                .ifPresent(region -> assertThat(resultRegion.name()).isEqualTo(region.getName()));
        }

        for (var resultRegion : result.abroad()) {
            regionsMap.get(MissionaryRegionType.ABROAD).stream()
                .filter(region -> region.getId().equals(resultRegion.id()))
                .findFirst()
                .ifPresent(region -> assertThat(resultRegion.name()).isEqualTo(region.getName()));
        }

        assertThat(result.regions()).hasSize(regionsMap.size());
        verify(missionaryRegionService, times(1)).getMissionaryRegionTypeMissionaryRegionsMap();
        verifyNoMoreInteractions(missionaryRegionService);
    }

    @Test
    void 선교_지역에_해당하는_선교_목록_조회() {
        // Given
        final Page<Missionary> page = mock(PageImpl.class);
        final var totalPages = 10;

        when(page.getContent()).thenReturn(
            List.of(
                Missionary.builder()
                    .id("1")
                    .name("선교")
                    .pastor(
                        Pastor.builder()
                            .name("목사")
                            .build()
                    )
                    .period(
                        Period.builder()
                            .startDate(OffsetDateTime.now())
                            .endDate(OffsetDateTime.now().plusMonths(1)
                            )
                            .build()
                    )
                    .build()
            )
        );
        when(page.getTotalPages()).thenReturn(totalPages);
        when(page.getNumber()).thenReturn(0);
        when(page.getTotalElements()).thenReturn(10L);
        when(missionaryService.getMissionariesByRegion(any(GetMissionariesByRegionQuery.class)))
            .thenReturn(page);

        // When
        final var result = missionaryManagement.getMissionariesByRegion(
            new GetMissionariesByRegionQuery("regionId", 10, 1)
        );

        // Then
        assertThat(result).isNotNull();
        assertThat(result.missionaries()).hasSize(1);
        assertThat(result.totalCount()).isEqualTo(10);
        assertThat(result.totalPages()).isEqualTo(totalPages + 1);
        assertThat(result.currentPage()).isEqualTo(1);
        verify(missionaryService, times(1)).getMissionariesByRegion(any(GetMissionariesByRegionQuery.class));
        verifyNoMoreInteractions(missionaryService);
    }

    @Test
    void 선교_생성() throws CommonException {
        // Given
        final var getMemberServiceTypeDto = mock(GetMemberServiceTypeDto.class);
        when(getMemberServiceTypeDto.serviceType()).thenReturn(ServiceType.ADMIN_SERVICE);

        when(memberExternalService.getMemberServiceType(anyString())).thenReturn(
            getMemberServiceTypeDto
        );

        when(missionaryService.createMissionary(any(CreateMissionaryCommand.class)))
            .thenReturn("missionaryId");

        final var command = new CreateMissionaryCommand(
            "regionId",
            "선교",
            OffsetDateTime.now(),
            OffsetDateTime.now().plusMonths(1),
            "목사 이름"
        );

        // When
        final var result = missionaryManagement.createMissionary(
            "admin",
            command
        );

        // Then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo("missionaryId");
        verify(getMemberServiceTypeDto, times(1)).serviceType();
        verify(memberExternalService, times(1)).getMemberServiceType("admin");
        verify(missionaryService, times(1)).createMissionary(command);
        verifyNoMoreInteractions(getMemberServiceTypeDto, memberExternalService, missionaryService);

    }

    @Test
    void 선교_생성_실패_권한_없음() throws CommonException {
        // Given
        final var getMemberServiceTypeDto = mock(GetMemberServiceTypeDto.class);
        when(getMemberServiceTypeDto.serviceType()).thenReturn(ServiceType.USER_SERVICE);
        when(memberExternalService.getMemberServiceType(anyString())).thenReturn(getMemberServiceTypeDto);

        final var command = new CreateMissionaryCommand(
            "regionId",
            "선교",
            OffsetDateTime.now(),
            OffsetDateTime.now().plusMonths(1),
            "목사 이름"
        );

        // When & Then
        assertThatThrownBy(() -> missionaryManagement.createMissionary("admin", command))
            .isInstanceOf(AccessDeniedMissionaryStaffException.class);

        verify(memberExternalService, times(1)).getMemberServiceType("admin");
        verifyNoMoreInteractions(memberExternalService);
    }


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


    @Test
    void 선교_준비팀_해임() throws CommonException {
        // Given
        final var memberId = "memberId";
        final var userIds = List.of("userId");
        final var missionaryId = "missionaryId";

        when(memberExternalService.getMemberServiceType(memberId)).thenReturn(
            new GetMemberServiceTypeDto(ServiceType.ADMIN_SERVICE)
        );

        // When
        missionaryManagement.disappointMissionaryStaffs(
            memberId,
            new DisappointMissionaryStaffsCommand(
                missionaryId,
                userIds
            )
        );

        // Then
        verify(memberExternalService, times(1)).getMemberServiceType(memberId);
        verify(missionaryStaffService, times(1)).disappointMissionaryStaffs(
            missionaryId,
            userIds
        );
        verifyNoMoreInteractions(missionaryStaffService, memberExternalService);
    }
}
