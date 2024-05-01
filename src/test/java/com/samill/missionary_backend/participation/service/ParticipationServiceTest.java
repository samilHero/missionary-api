package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.MissionaryBackendApplication;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.participation.dto.CreateParticipationDto;
import com.samill.missionary_backend.participation.dto.UpdateParticipationDto;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = MissionaryBackendApplication.class)
class ParticipationServiceImplTest {
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private ParticipationRepository participationRepository;
    private ParticipationMapper participationMapper;
    String missionaryId = UUID.randomUUID().toString();

    @AfterEach
    void tearDown() {
        participationRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("선교를 신청할 때 정원이 차면 신청불가")
    void 선착순_선교_테스트() throws InterruptedException{
        //given
        int userCount = 100;
        int missionaryMaxCount = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(userCount);

        for(int i=0;i<userCount;i++) {
            CreateParticipationDto createParticipationDto = CreateParticipationDto.builder()
                    .isPaid(true)
                    .missionaryId(missionaryId)
                    .applyFee(10000)
                    .identificationNumber("9802321111222")
                    .build();

            executorService.submit(() -> {
                try {
                    participationService.participate(createParticipationDto, missionaryMaxCount);
                } catch (CommonException e) {
                    System.out.println(e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // 리스너 응답을 늦게 받는 경우를 대비해 테스트를 지연시킨다.
        Thread.sleep(12000);

        List<Participation> participationList = participationRepository.findByMissionaryId(missionaryId);
        //then
        assertEquals(missionaryMaxCount, participationList.size());
    }

    @Test
    @DisplayName("선교 취소시 deleted_at 수정함. 조회시 조회가 되면 안된다.")
    @Transactional
    void 선교_취소_테스트() {
        // given
        CreateParticipationDto createParticipationDto = CreateParticipationDto.builder()
                        .missionaryId(missionaryId)
                        .applyFee(10000)
                        .isPaid(true)
                        .identificationNumber("99991111").build();
        Participation participation =
                participationRepository.save(participationMapper.INSTANCE.createParticipationDtoToEntity(createParticipationDto));

        UpdateParticipationDto updateParticipationDto = UpdateParticipationDto.builder()
                .id(participation.getId())
                .missionaryId(missionaryId)
                .build();

        //when
        participationService.cancelParticipation(updateParticipationDto);

        //then
        Optional<Participation> result = participationRepository.findById(updateParticipationDto.getId());
        assertTrue(result.isEmpty());
    }
}