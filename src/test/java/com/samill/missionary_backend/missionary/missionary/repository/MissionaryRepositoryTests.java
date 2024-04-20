package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.RepositoryTestConfig;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.entity.MissionaryPoster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(RepositoryTestConfig.class)
class MissionaryRepositoryTests {

    @Autowired
    private MisisonaryRepository missionaryRepository;

    @Test
    void saveMissionaryTest() {
        Missionary missionary = Missionary.builder()
                .id(UUID.randomUUID().toString())
                .price(1000)
                .participationPeriod(
                        Period.builder()
                                .startDate(OffsetDateTime.now().plusDays(3))
                                .endDate(OffsetDateTime.now().plusDays(10))
                                .build()
                )
                .workPeriod(
                        Period.builder()
                                .startDate(OffsetDateTime.now().plusDays(10))
                                .endDate(OffsetDateTime.now().plusDays(20))
                                .build()
                )

                .pastor(
                        Pastor.builder()
                                .name("염동욱")
                                .phone("01023937318")
                                .build()
                )
                .posters(Arrays.asList(
                                MissionaryPoster.builder()
                                        .id(UUID.randomUUID().toString())
                                        .name("선교 이미지1")
                                        .path("https://samill.com")
                                        .build(),
                                MissionaryPoster.builder()
                                        .id(UUID.randomUUID().toString())
                                        .name("선교 이미지2")
                                        .path("https://samill.com")
                                        .build()
                        )
                )
                .build();

        final Missionary savedMissionary = missionaryRepository.save(missionary);

        System.out.println(missionaryRepository.findAllInParticipationPeriod(OffsetDateTime.now()
                .plusDays(5)));

    }

}
