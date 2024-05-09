package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.*;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipantCountRepository;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ParticipantCountRepository participantCountRepository;
    private final RabbitMqProducer rabbitMqProducer;

    @Override
    @Transactional
    public void createParticipation(CreateParticipationCommand createParticipationCommand, int maxUserCount) throws CommonException{
        validateCreateParticipation(createParticipationCommand, maxUserCount);

        // 메시지 리스너에서 신청내역 저장
        rabbitMqProducer.sendMessage(new MessageDto(createParticipationCommand));
    }

    @Override
    public void deleteParticipation(DeleteParticipationCommand deleteParticipationCommand) throws CommonException {
        String missionaryId = deleteParticipationCommand.getMissionaryId();
        validateDeleteParticipation(deleteParticipationCommand);
        Participation participation = participationRepository.findById(deleteParticipationCommand.getId())
                .orElseThrow(() -> new CommonException(ResponseCode.PARTICIPATION_NOT_FOUND));
        participationRepository.delete(participation);
        participantCountRepository.decrement(missionaryId);
    }

    @Override
    public List<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery) {
        return participationRepository.findAllByConditionOrderByCreatedAtAsc(getParticipationsQuery);
    }
    @Override
    public void updateParticipation(UpdateParticipationCommand updateParticipationCommand) throws CommonException {
        Participation participation = participationRepository.findById(updateParticipationCommand.getId())
                .orElseThrow(() -> new CommonException(ResponseCode.PARTICIPATION_NOT_FOUND));
        participation.updateIdentificationNumber(updateParticipationCommand.getIdentificationNumber());
        participationRepository.save(participation);
    }

    @Override
    public GetParticipationQueryResult getParticipation(String participationId) throws CommonException {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new CommonException(ResponseCode.PARTICIPATION_NOT_FOUND));
        return ParticipationMapper.INSTANCE.entityToGetParticipationQueryResult(participation);
    }

    private void validateCreateParticipation(CreateParticipationCommand createParticipationDto, int maxUserCount) throws CommonException {
        //participationPeriod validation 필요
        Participation participation = participationRepository.findByUserId(createParticipationDto.getUserId());

        if (Objects.nonNull(participation)) {
            throw new CommonException(ResponseCode.PARTICIPATION_ALREADY_PARTICIPATED);
        }

        Long count = participantCountRepository.increment(createParticipationDto.getMissionaryId());

        if (count > maxUserCount) {
            participantCountRepository.set(createParticipationDto.getMissionaryId(), String.valueOf(maxUserCount));
            throw new CommonException(ResponseCode.PARTICIPATION_MAXIMUM_EXCEEDED);
        }
    }

    private void validateDeleteParticipation(DeleteParticipationCommand deleteParticipationCommand) throws CommonException {
        //participationPeriod validation 필요
        Participation participation = participationRepository.findByIdAndUserId(deleteParticipationCommand.getId(), deleteParticipationCommand.getUserId());
        if (Objects.isNull(participation)) {
            throw new CommonException(ResponseCode.PARTICIPATION_NOT_ENROLLED);
        }
    }
}
