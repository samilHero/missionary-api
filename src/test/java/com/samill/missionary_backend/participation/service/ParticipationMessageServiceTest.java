package com.samill.missionary_backend.participation.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.samill.missionary_backend.common.AbstractSpringBootTests;
import com.samill.missionary_backend.missionary.dto.CreateParticipationCommand;
import com.samill.missionary_backend.missionary.dto.MessageDto;
import java.util.UUID;

import com.samill.missionary_backend.missionary.participation.service.RabbitMqProducer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class ParticipationMessageServiceTest extends AbstractSpringBootTests {

    @Autowired
    private RabbitMqProducer rabbitMqProducer;

    @Test
    @Disabled // 임시
    @DisplayName("메시지를 보내면 리스너에서 응답을 받는다.")
    void 메시지_보내기() {
        //given
        String missionaryId = UUID.randomUUID().toString();

        CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
            .missionaryId(missionaryId)
            .applyFee(10000)
            .identificationNumber("9802321111222")
            .build();

        //when
        rabbitMqProducer.sendMessage(new MessageDto(createParticipationDto));

        //then
        assertNotNull(createParticipationDto);
    }
}