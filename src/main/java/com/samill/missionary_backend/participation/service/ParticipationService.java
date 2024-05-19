package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;

public interface ParticipationService {
    void createParticipation(CreateParticipationCommand createParticipationDto, int maxCount) throws CommonException;
    void deleteParticipation(String participationId, DeleteParticipationCommand deleteParticipationCommand) throws CommonException;
    void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException;
    Page<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery, Pageable pageable);
    GetParticipationQueryResult getParticipation(String participationId) throws CommonException;
    void updateParticipationPrivacyInfo(List<String> list);
}
