package com.samill.missionary_backend.missionary;


import static org.assertj.core.api.Assertions.assertThat;

import com.samill.missionary_backend.common.AbstractSpringBootTestsBase;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResultRegion;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;


public class MissionaryManagementTests extends AbstractSpringBootTestsBase {

    @Test
    void 선교_지역_목록_조회() {
        final var result = missionaryExternalService.getMissionaryRegions();

        assertThat(result).isNotNull();
        assertThat(result.abroad()).isNotNull();
        assertThat(result.domestic()).isNotNull();

        for (var region : result.regions()) {
            assertThat(region).isNotNull();
            assertThat(region.id()).isNotNull();
            assertThat(region.name()).isNotNull();
        }
    }

    @Test
    void 선교_지역에_해당하는_선교_목록_조회() {
        final var regionIds = missionaryExternalService.getMissionaryRegions()
            .regions()
            .stream()
            .map(GetMissionaryRegionsQueryResultRegion::id)
            .toList();

        for (final var regionId : regionIds) {
            final var result = missionaryExternalService.getMissionariesByRegion(new GetMissionariesByRegionQuery(regionId, null, null));

            for (var missionary : result.missionaries()) {
                assertThat(missionary).isNotNull();
                assertThat(missionary.id()).isNotNull();
                assertThat(missionary.id()).isNotNull();
                assertThat(missionary.name()).isNotNull();
                assertThat(missionary.pastorName()).isNotNull();
                assertThat(missionary.startDate()).isNotNull();
                assertThat(missionary.endDate()).isNotNull();
            }

            assertThat(result.currentPage()).isGreaterThanOrEqualTo(1);
            assertThat(result.totalPages()).isGreaterThanOrEqualTo(1);
            assertThat(result.totalPages()).isGreaterThanOrEqualTo(result.currentPage());
        }
    }

    @Test
    void 선교_생성() throws MissionaryException {
        final var regionIds = missionaryExternalService.getMissionaryRegions()
            .regions()
            .stream()
            .map(GetMissionaryRegionsQueryResultRegion::id)
            .toList();

        for (final var regionId : regionIds) {
            final var result = missionaryExternalService.createMissionary(
                new CreateMissionaryCommand(
                    regionId,
                    "선교",
                    OffsetDateTime.now(),
                    OffsetDateTime.now().plusMonths(1),
                    "목사 이름"
                )
            );

            assertThat(result).isNotNull();
            assertThat(result.id()).isNotNull();
        }

    }
}
