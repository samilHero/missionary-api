package com.samill.missionary_backend.participation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.samill.missionary_backend.common.AbstractSpringBootTests;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;


//@ContextConfiguration(classes = MissionaryBackendApplication.class)
class ParticipationServiceTest extends AbstractSpringBootTests {

    @Autowired
    private ParticipationService participationService;
    @Autowired
    private ParticipationRepository participationRepository;

//    @AfterEach
//    void tearDown() {
//        participationRepository.deleteAllInBatch();
//    }

//    @Test
//    @Disabled
//    @DisplayName("선교를 신청할 때 정원이 차면 신청불가")
//    @Transactional
//    void 선착순_선교_테스트() throws CommonException {
//        String missionaryId = UUID.randomUUID().toString();
//        String userId;
//
//        //given
//        int userCount = 10;
//        int missionaryMaxCount = 10;
//
//        for(int i=0;i<userCount;i++) {
//            userId = UUID.randomUUID().toString();
//            CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
//                    .missionaryId(missionaryId)
//                    .applyFee(10000)
//                    .identificationNumber("9802321111222")
//                    .memberId("UUIDD1")
//                    .userId(userId)
//                    .name("홍길동")
//                    .build();
//            System.out.println(userCount);
//            participationService.createParticipation(createParticipationDto, missionaryMaxCount);
//
//        }
//
//        // 리스너 응답을 늦게 받는 경우를 대비해 테스트를 지연시킨다.
////        Thread.sleep(10000);
//
////        List<Participation> participationList = participationRepository.findByMissionaryId(missionaryId);
//
//        //then
////        assertEquals(missionaryMaxCount, participationList.size());
//    }

    @Test
    @DisplayName("선교 취소시 deleted_at 수정함. 조회시 조회가 되면 안된다.")
    @Transactional
    void 선교_취소_테스트() throws CommonException {
        String missionaryId = UUID.randomUUID().toString();
        // given
        CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
            .missionaryId(missionaryId)
            .applyFee(10000)
            .identificationNumber("980232-1112220")
            .memberId("UUIDD1")
            .userId("kdf1")
            .name("홍길동")
            .isOwnCar(false)
            .build();

        Participation participation =
            participationRepository.save(ParticipationMapper.INSTANCE.createParticipationCommandToEntity(createParticipationDto));

        DeleteParticipationCommand deleteParticipationCommand = DeleteParticipationCommand.builder()
            .missionaryId(missionaryId)
            .userId("kdf1")
            .build();

        //when
        participationService.deleteParticipation(participation.getId(), deleteParticipationCommand);

        //then
        Optional<Participation> result = participationRepository.findById(participation.getId());
        assertTrue(result.isEmpty());
    }

    @Test
    @Transactional
    void 목록조회() {
        // given
        String missionaryId = UUID.randomUUID().toString();
        for (int i = 0; i < 10; i++) {
            CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
                    .missionaryId(missionaryId)
                    .applyFee(10000)
                    .identificationNumber("980232-1112220")
                    .memberId("UUIDD1")
                    .birthDate("19940616")
                    .userId("kdf1")
                    .name("홍길동")
                    .isOwnCar(false)
                    .build();

            participationRepository.save(ParticipationMapper.INSTANCE.createParticipationCommandToEntity(createParticipationDto));
        }

        //when
        GetParticipationsQuery getParticipationsQuery = GetParticipationsQuery.builder()
            .missionaryId(missionaryId)
            .build();
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<GetParticipationQueryResult> participationQueryResults = participationService.getParticipations(getParticipationsQuery, pageRequest);

        //then
        assertEquals(3, participationQueryResults.getSize());
    }
}