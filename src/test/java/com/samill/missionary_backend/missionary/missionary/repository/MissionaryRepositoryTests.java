package com.samill.missionary_backend.missionary.missionary.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
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
    void 지역별_최신_선교_목록_조회() {
        assertThat(missionaryRepository.findLatestMissionariesByRegion()).isNotEmpty();
    }


}
