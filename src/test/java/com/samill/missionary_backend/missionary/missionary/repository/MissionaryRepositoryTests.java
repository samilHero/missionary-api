package com.samill.missionary_backend.missionary.missionary.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({JpaConfig.class, DateTimeProviderConfig.class})
class MissionaryRepositoryTests {

    @Autowired
    private MissionaryRepository missionaryRepository;


    @Test
    void 지역_선교_목록_조회() {
        assertThat(missionaryRepository.findByRegion_IdOrderByPeriod_EndDateDesc("0b6a5e32-dc34-4a34-8393-0e5ce6e44b0a", null)).isNotEmpty();
    }

    @Test
    void 선교_생성() {
        final var missionary = Missionary.builder()
            .name("선교 이름")
            .region(
                MissionaryRegion.builder()
                    .id("id")
                    .build()
            )
            .period(
                Period.builder()
                    .startDate(OffsetDateTime.now())
                    .endDate(OffsetDateTime.now().plusMonths(1))
                    .build()
            )
            .pastor(
                Pastor.builder()
                    .name("pastor name")
                    .phone("010-1234-5678")
                    .build()
            )
            .build();

        final var savedMissionary = missionaryRepository.save(missionary);

        assertThat(savedMissionary).isNotNull();
        assertThat(savedMissionary.getId()).isNotNull();
        assertThat(savedMissionary.getName()).isEqualTo(missionary.getName());
        assertThat(savedMissionary.getPeriod()).isEqualTo(missionary.getPeriod());
        assertThat(savedMissionary.getPastor()).isEqualTo(missionary.getPastor());
    }


}
