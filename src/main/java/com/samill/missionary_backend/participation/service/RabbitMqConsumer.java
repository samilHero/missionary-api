package com.samill.missionary_backend.participation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.MessageDto;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConsumer {
    private final ParticipationRepository participationRepository;

    /**
     * Queue에서 메시지를 구독
     *
     * @param messageDto
     */
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(MessageDto messageDto) {
        log.info("Received message: {}", messageDto.toString());
        saveParticipation(messageDto.getObject());
    }

    private void saveParticipation(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateParticipationCommand createParticipationDto = objectMapper.convertValue(object, CreateParticipationCommand.class);
        Participation participation = ParticipationMapper.INSTANCE.createParticipationCommandToEntity(createParticipationDto);
        participationRepository.save(participation);
    }
}
