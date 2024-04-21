package com.samill.missionary_backend.participation.infrastructure.repository;

import com.samill.missionary_backend.config.DataJpaTestConfig;
import com.samill.missionary_backend.participation.domain.dto.ParticipationDto;
import com.samill.missionary_backend.participation.infrastructure.entity.Participation;
import com.samill.missionary_backend.participation.infrastructure.mapper.ParticipationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(DataJpaTestConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ParticipationRepositoryTest {
    @Autowired
    ParticipationRepository participationRepository;
    ParticipationMapper participationMapper;

    @Test
    void entity_테스트() {
        ParticipationDto participationDto = ParticipationDto.builder()
                .applyFee(10000)
                .isPaid(true)
                .identificationNumber("2020202020")
                .build();
        Participation participation = participationMapper.INSTANCE.dtoToEntity(participationDto);
        Participation response = participationRepository.save(participation);
        assertEquals(10000,response.getApplyFee());
    }
}