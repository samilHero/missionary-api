package com.samill.missionary_backend.missionary.participation.service;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.*;
import com.samill.missionary_backend.missionary.participation.entity.Participation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipationService {
    void createParticipation(CreateParticipationCommand createParticipationDto, int maxCount) throws CommonException;
    void deleteParticipation(String participationId, DeleteParticipationCommand deleteParticipationCommand) throws CommonException;
    void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException;
    Page<GetParticipationQueryResult> getParticipations(String missionaryId, GetParticipationsQuery getParticipationsQuery, Pageable pageable);
    Participation getParticipation(String participationId) throws CommonException;
    void updateParticipationPrivacyInfo(List<String> list);
}
