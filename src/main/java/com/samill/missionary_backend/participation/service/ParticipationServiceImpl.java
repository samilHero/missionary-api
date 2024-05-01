package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.CreateParticipationDto;
import com.samill.missionary_backend.participation.dto.MessageDto;
import com.samill.missionary_backend.participation.dto.UpdateParticipationDto;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipantCountRepository;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationMapper participationMapper;
    private final ParticipationRepository participationRepository;
    private final ParticipantCountRepository participantCountRepository;
    private final RabbitMqProducer rabbitMqProducer;

    @Override
    @Transactional
    public void participate(CreateParticipationDto createParticipationDto, int maxUserCount) throws CommonException{
        String missionaryId = createParticipationDto.getMissionaryId();
        Long count = participantCountRepository.increment(missionaryId);
        System.out.println("Controller.postInput Thread: "+Thread.currentThread());
        if (count > maxUserCount) {
            participantCountRepository.set(missionaryId, String.valueOf(maxUserCount));
            throw new CommonException(ResponseCode.PARTICIPATION_NOT_ALLOWED);
        }
        // 리스너에서 신청내역 저장
        rabbitMqProducer.sendMessage(new MessageDto(createParticipationDto));
    }

    @Override
    public void cancelParticipation(UpdateParticipationDto updateParticipationDto) {
        String missionaryId = updateParticipationDto.getMissionaryId();
        participantCountRepository.decrement(missionaryId);
        Participation participation = participationMapper.INSTANCE.updateParticipationDtoToEntity(updateParticipationDto);
        participationRepository.delete(participation);
    }

    @Override
    public List<Participation> getParticipations(String missionaryId) {
        return participationRepository.findByMissionaryId(missionaryId);
    }

    @Override
    public void updateParticipation(CreateParticipationDto participationDto) {

    }
}
