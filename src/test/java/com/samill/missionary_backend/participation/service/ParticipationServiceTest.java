package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.MissionaryBackendApplication;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@ContextConfiguration(classes = MissionaryBackendApplication.class)
class ParticipationServiceImplTest {
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private ParticipationRepository participationRepository;

    @AfterEach
    void tearDown() {
        participationRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("선교를 신청할 때 정원이 차면 신청불가")
    void 선착순_선교_테스트() throws InterruptedException{
        String missionaryId = UUID.randomUUID().toString();
        //given
        int userCount = 100;
        int missionaryMaxCount = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(userCount);

        for(int i=0;i<userCount;i++) {
            CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
                    .missionaryId(missionaryId)
                    .applyFee(10000)
                    .identificationNumber("9802321111222")
                    .memberId("UUIDD1")
                    .userId("kdf1")
                    .name("홍길동")
                    .build();

            executorService.submit(() -> {
                try {
                    participationService.createParticipation(createParticipationDto, missionaryMaxCount);
                } catch (CommonException e) {
                    System.out.println(e.getResponseCode().getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // 리스너 응답을 늦게 받는 경우를 대비해 테스트를 지연시킨다.
        Thread.sleep(15000);

        List<Participation> participationList = participationRepository.findByMissionaryId(missionaryId);

        //then
        assertEquals(missionaryMaxCount, participationList.size());
    }

    @Test
    @DisplayName("선교 취소시 deleted_at 수정함. 조회시 조회가 되면 안된다.")
    @Transactional
    void 선교_취소_테스트() throws CommonException {
        String missionaryId = UUID.randomUUID().toString();
        // given
        CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
                .missionaryId(missionaryId)
                .applyFee(10000)
                .identificationNumber("9802321111222")
                .memberId("UUIDD1")
                .userId("kdf1")
                .name("홍길동")
                .build();

        Participation participation =
                participationRepository.save(ParticipationMapper.INSTANCE.createParticipationCommandToEntity(createParticipationDto));


        DeleteParticipationCommand deleteParticipationCommand = DeleteParticipationCommand.builder()
                .id(participation.getId())
                .missionaryId(missionaryId)
                .userId("kdf1")
                .build();

        //when
        participationService.deleteParticipation(deleteParticipationCommand);

        //then
        Optional<Participation> result = participationRepository.findById(deleteParticipationCommand.getId());
        assertTrue(result.isEmpty());
    }

    @Test
    @Transactional
    void 목록조회() {
        // given
        String missionaryId = UUID.randomUUID().toString();
        for(int i=0; i<10;i++) {
            CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
                    .missionaryId(missionaryId)
                    .applyFee(10000)
                    .identificationNumber("9802321111222")
                    .memberId("UUIDD1")
                    .userId("kdf1")
                    .name("홍길동")
                    .build();

            participationRepository.save(ParticipationMapper.INSTANCE.createParticipationCommandToEntity(createParticipationDto));
        }

        //when
        GetParticipationsQuery getParticipationsQuery = GetParticipationsQuery.builder()
                .missionaryId(missionaryId)
                .pageSize(5)
                .build();
        List<GetParticipationQueryResult> participationQueryResults = participationService.getParticipations(getParticipationsQuery);

        //then
        assertEquals(5, participationQueryResults.size());
    }
}