package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.MessageDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ParticipationMessageServiceTest {
    @Autowired
    private RabbitMqProducer rabbitMqProducer;
    @Test
    @DisplayName("메시지를 보내면 리스너에서 응답을 받는다.")
    void 메시지_보내기() {
        //given
        String missionaryId = UUID.randomUUID().toString();

        CreateParticipationCommand createParticipationDto = CreateParticipationCommand.builder()
                .isPaid(true)
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