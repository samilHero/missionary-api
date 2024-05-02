package com.samill.missionary_backend.participation.repository;

import com.samill.missionary_backend.participation.dto.CreateParticipationDto;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.service.IdentificationNumberConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ParticipationRepositoryTest {
    @Autowired
    private ParticipationRepository participationRepository;
    private ParticipationMapper participationMapper;
    private IdentificationNumberConverter identificationNumberConverter;

    @Test
    @Transactional
    void 참가신청_주민번호_암호화_테스트() {
        // given
        String missionaryId = UUID.randomUUID().toString();
        CreateParticipationDto createParticipationDto = CreateParticipationDto.builder()
                .isPaid(true)
                .missionaryId(missionaryId)
                .applyFee(10000)
                .identificationNumber("9802321111222")
                .build();

        // when
        Participation participation = participationMapper.INSTANCE.createParticipationDtoToEntity(createParticipationDto);
        Participation savedResult = participationRepository.save(participation);

        // then
        assertEquals("9802321111222", savedResult.getIdentificationNumber());
    }
}