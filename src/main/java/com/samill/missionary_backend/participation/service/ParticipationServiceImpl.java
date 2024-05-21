package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.event.UpdateParticipationEvent;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.CreateParticipationCommand;
import com.samill.missionary_backend.participation.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import com.samill.missionary_backend.participation.dto.MessageDto;
import com.samill.missionary_backend.participation.dto.UpdateParticipationCommand;
import com.samill.missionary_backend.participation.entity.Participation;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.repository.ParticipantCountRepository;
import com.samill.missionary_backend.participation.repository.ParticipationRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;
    private final ParticipantCountRepository participantCountRepository;
    private final RabbitMqProducer rabbitMqProducer;
    private final ApplicationEventPublisher events;

    @Override
    @Transactional
    public void createParticipation(CreateParticipationCommand createParticipationCommand, int maxUserCount) throws CommonException {
        validateCreateParticipation(createParticipationCommand, maxUserCount);

        // 메시지 리스너에서 신청내역 저장
        rabbitMqProducer.sendMessage(new MessageDto(createParticipationCommand));
    }

    @Override
    public void deleteParticipation(String participationId, DeleteParticipationCommand deleteParticipationCommand) throws CommonException {
        validateDeleteParticipation(participationId, deleteParticipationCommand);
        Participation participation = participationRepository.findById(participationId)
            .orElseThrow(() -> new CommonException(ResponseCode.PARTICIPATION_NOT_FOUND));
        participationRepository.delete(participation);
        participantCountRepository.decrement(deleteParticipationCommand.getMissionaryId());
        publishEvent(participation);
    }

    @Override
    public Page<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery, Pageable pageable) {
        return participationRepository.findAllByQuery(getParticipationsQuery, pageable);
    }

    @Override
    public void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException {
        Participation participation = participationRepository.findById(participationId)
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

    @Override
    public void updateParticipationPrivacyInfo(List<String> list) {
        // 선교 종료일 >= beforeDate 인 경우 주민번호 삭제
        List<Participation> participationList = list.stream()
            .map(missionaryId -> participationRepository.findByMissionaryId(missionaryId))
            .flatMap(List::stream)
            .peek(data -> data.updateIdentificationNumber(""))
            .collect(Collectors.toList());
        participationRepository.saveAll(participationList);
    }

    private void validateCreateParticipation(CreateParticipationCommand createParticipationDto, int maxUserCount) throws CommonException {
        //participationPeriod validation 필요
        Participation participation = participationRepository.findByUserIdAndMissionaryId(createParticipationDto.getUserId(),
            createParticipationDto.getMissionaryId());

        if (Objects.nonNull(participation)) {
            throw new CommonException(ResponseCode.PARTICIPATION_ALREADY_PARTICIPATED);
        }

        Long count = participantCountRepository.increment(createParticipationDto.getMissionaryId());

        if (count > maxUserCount) {
            participantCountRepository.set(createParticipationDto.getMissionaryId(), String.valueOf(maxUserCount));
            throw new CommonException(ResponseCode.PARTICIPATION_MAXIMUM_EXCEEDED);
        }
    }

    private void validateDeleteParticipation(String participationId, DeleteParticipationCommand deleteParticipationCommand) throws CommonException {
        Participation participation = participationRepository.findByIdAndUserIdAndMissionaryId(participationId,
            deleteParticipationCommand.getUserId(), deleteParticipationCommand.getMissionaryId());
        if (Objects.isNull(participation)) {
            throw new CommonException(ResponseCode.PARTICIPATION_NOT_ENROLLED);
        }
    }

    // 선교 신청 인원수 이벤트 발행
    private void publishEvent(Participation participation) {
        Integer count = participantCountRepository.get(participation.getMissionaryId());
        events.publishEvent(new UpdateParticipationEvent(participation.getMissionaryId(), count));
    }
}
