package com.samill.missionary_backend.participation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.samill.missionary_backend.common.AbstractSpringBootTests;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


class ParticipationRepositoryTest extends AbstractSpringBootTests {

    @Autowired
    private ParticipationRepository participationRepository;

    @Test
    @Transactional
    void 참가신청_주민번호_암호화_테스트() {
        // given
        String missionaryId = UUID.randomUUID().toString();
        CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
            .missionaryId(missionaryId)
            .applyFee(10000)
            .identificationNumber("9802321111222")
            .userId("kkk1")
            .name("홍길동")
            .memberId("UUIDf1")
            .build();

        // when
        Participation participation = ParticipationMapper.INSTANCE.createParticipationCommandToEntity(createParticipationDto);
        Participation savedResult = participationRepository.save(participation);

        // then
        assertEquals("9802321111222", savedResult.getIdentificationNumber());
    }
}