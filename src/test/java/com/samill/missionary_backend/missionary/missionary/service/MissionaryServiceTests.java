package com.samill.missionary_backend.missionary.missionary.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQuery;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.repository.MissionaryRepository;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({JpaConfig.class, DateTimeProviderConfig.class})
class MissionaryServiceTests {

    @InjectMocks
    private MissionaryService missionaryService;

    @Mock
    private MissionaryRepository missionaryRepository;


    @Test
    void 지역_선교_목록_조회() {
        // GIVEN
        final var missionaries = List.of(
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .name("선교1")
                .build(),
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .name("선교2")
                .build()

        );

        final var missionaryPage = mock((PageImpl.class));
        when(missionaryPage.getSize()).thenReturn(10);
        when(missionaryPage.getTotalElements()).thenReturn(2L);
        when(missionaryPage.getTotalPages()).thenReturn(1);
        when(missionaryPage.getNumber()).thenReturn(0);
        when(missionaryPage.getContent()).thenReturn(missionaries);
        when(missionaryRepository.findByRegion_IdOrderByPeriod_EndDateDesc(anyString(), any(Pageable.class)))
            .thenReturn(missionaryPage);

        // WHEN
        final var result = missionaryService.getMissionariesByRegion(
            new GetMissionariesByRegionQuery("region_id", null, null)
        );

        // THEN
        assertThat(result.getContent()).isEqualTo(missionaries);
        assertThat(result.getTotalElements()).isEqualTo(missionaryPage.getTotalElements());
        assertThat(result.getTotalPages()).isEqualTo(missionaryPage.getTotalPages());
        assertThat(result.getNumber()).isEqualTo(missionaryPage.getNumber());
        assertThat(result.getSize()).isEqualTo(missionaryPage.getSize());
    }

    @Test
    void 선교_생성() {
        // GIVEN
        final var createMissionaryCommand = new CreateMissionaryCommand(
            "region_id",
            "테스트 선교",
            OffsetDateTime.now(),
            OffsetDateTime.now().plusMonths(1),
            "테스트 목사"
        );

        final var missionary = Missionary.builder()
            .id(UUID.randomUUID().toString())
            .name(createMissionaryCommand.name())
            .period(
                Period.builder()
                    .startDate(createMissionaryCommand.startDate())
                    .endDate(createMissionaryCommand.endDate())
                    .build()
            )
            .region(
                MissionaryRegion.builder()
                    .id(createMissionaryCommand.regionId())
                    .build()
            )
            .pastor(
                Pastor.builder()
                    .name(createMissionaryCommand.pastorName())
                    .build()
            )
            .build();

        when(missionaryRepository.save(any(Missionary.class)))
            .thenReturn(missionary);

        // WHEN
        final var missionaryId = missionaryService.createMissionary(createMissionaryCommand);

        // THEN
        assertThat(missionaryId).isEqualTo(missionary.getId());
        verify(missionaryRepository).save(missionary);
        verifyNoMoreInteractions(missionaryRepository);
    }


}
