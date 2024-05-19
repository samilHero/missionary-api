package com.samill.missionary_backend.participation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.MessageDto;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.event.UpdateParticipationEvent;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipantCountRepository;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConsumer {
    private final ParticipationRepository participationRepository;
    private final ParticipantCountRepository participantCountRepository;
    private final ApplicationEventPublisher events;

    /**
     * Queue에서 메시지를 구독
     *
     * @param messageDto
     */
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(MessageDto messageDto) {
        log.info("Received message: {}", messageDto.toString());
        Participation participation = saveParticipation(messageDto.getObject());
        Integer count = participantCountRepository.get(participation.getMissionaryId());
        // 선교 신청 인원수 이벤트 발행
        events.publishEvent(new UpdateParticipationEvent(participation.getMissionaryId(), count));
    }

    private Participation saveParticipation(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateParticipationCommand createParticipationDto = objectMapper.convertValue(object, CreateParticipationCommand.class);
        Participation participation = ParticipationMapper.INSTANCE.createParticipationCommandToEntity(createParticipationDto);
        return participationRepository.save(participation);
    }
}
