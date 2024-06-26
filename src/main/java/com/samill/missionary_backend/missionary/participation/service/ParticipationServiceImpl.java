package com.samill.missionary_backend.missionary.participation.service;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.event.ParticipationCanceled;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.CreateParticipationCommand;
import com.samill.missionary_backend.missionary.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsDownloadQuery;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import com.samill.missionary_backend.missionary.dto.MessageDto;
import com.samill.missionary_backend.missionary.dto.UpdateParticipationCommand;
import com.samill.missionary_backend.missionary.participation.entity.Participation;
import com.samill.missionary_backend.missionary.participation.repository.ParticipantCountRepository;
import com.samill.missionary_backend.missionary.participation.repository.ParticipationRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.NonNull;
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
    public Page<GetParticipationQueryResult> getParticipations(String missionaryId, GetParticipationsQuery getParticipationsQuery,
        Pageable pageable) {
        return participationRepository.findAllByQuery(missionaryId, getParticipationsQuery, pageable);
    }

    @Override
    public void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException {
        Participation participation = participationRepository.findById(participationId)
            .orElseThrow(() -> new CommonException(ResponseCode.PARTICIPATION_NOT_FOUND));
        participation.updateIdentificationNumber(updateParticipationCommand.getIdentificationNumber());
    }

    @Override
    public Participation getParticipation(String participationId) throws CommonException {
        Participation participation = participationRepository.findById(participationId)
            .orElseThrow(() -> new CommonException(ResponseCode.PARTICIPATION_NOT_FOUND));
        return participation;
    }

    @Override
    public void updateParticipationPrivacyInfo(List<String> list) {
        // 선교 종료일 >= beforeDate 인 경우 주민번호 삭제
        list.stream().map(participationRepository::findByMissionaryId)
            .flatMap(List::stream)
            .peek(data -> data.updateIdentificationNumber(""));
    }

    @Override
    public void updateParticipationPaid(List<String> ids) {
        ids.stream()
            .map(participationRepository::findById)
            .forEach(optParticipation -> optParticipation.ifPresent(participation -> participation.updateIsPaid(true)));
    }

    @Override
    public boolean isParticipating(@NonNull String missionaryId, @NonNull String userId) {
        return Objects.nonNull(participationRepository.findByUserIdAndMissionaryId(userId, missionaryId));
    }

    @Override
    public List<GetParticipationQueryResult> getParticipationsForDownload(@NonNull String missionaryId,
        GetParticipationsDownloadQuery getParticipationsDownloadQuery) {
        return participationRepository.findAllByQueryForCsv(missionaryId, getParticipationsDownloadQuery);
    }

    private void validateCreateParticipation(CreateParticipationCommand createParticipationDto, int maxUserCount) throws CommonException {
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

    // 선교 신청 취소 이벤트 발행
    private void publishEvent(Participation participation) {
        events.publishEvent(new ParticipationCanceled(participation.getMissionaryId()));
    }
}
