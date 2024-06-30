package com.samill.missionary_backend.missionary.missionary.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.enums.MissionaryRegion;
import com.samill.missionary_backend.missionary.missionary.repository.MissionaryRepository;
import java.time.OffsetDateTime;
import java.util.List;
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
class MissionaryServiceTests {

    @InjectMocks
    private MissionaryService missionaryService;

    @Mock
    private MissionaryRepository missionaryRepository;


    @Test
    void 선교_목록_그룹_조회() {
        // Given
        final var missionaries = List.of(
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .region(MissionaryRegion.ARMY)
                .name("군선교1")
                .build(),
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .region(MissionaryRegion.ARMY)
                .name("군선교2")
                .build(),
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .region(MissionaryRegion.SEOUL)
                .name("서울선교1")
                .build(),
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .region(MissionaryRegion.SEOUL)
                .name("서울선교2")
                .build(),
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .region(MissionaryRegion.JEJU)
                .name("제주선교1")
                .build(),
            Missionary.builder()
                .id(UUID.randomUUID().toString())
                .region(MissionaryRegion.JEJU)
                .name("제주선교2")
                .build()
        );

        when(
            missionaryRepository.findAllByMissionaryStaffs_UserIdAndPeriod_EndDateGreaterThanEqual(
                anyString(),
                any(OffsetDateTime.class)
            )
        ).thenReturn(missionaries);

        // When
        final var categoryMissionariesMap = missionaryService.getMissionariesByCategory("userId");

        // Then
        System.out.println(categoryMissionariesMap);
//        for(MissionaryRegion region : MissionaryRegion.values()) {
//            System.out.println(region);
//            System.out.println(categoryMissionariesMap.get(region));
//        }

//        [categoryMissionariesMap.values().forEach(]
//        System.out::println
//        );
//        categoryMissionariesMap.forEach((key, value) -> {
//            System.out.println(key);
//            value.forEach(missionary -> System.out.println(missionary.getName()));
//        });
    }


}
