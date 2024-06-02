package com.samill.missionary_backend.missionary.participation;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ParticipationExternalService {
    void createParticipation(CreateParticipationCommand createParticipationDto) throws Exception;
    void deleteParticipation(String participationId, DeleteParticipationCommand deleteParticipationCommand) throws CommonException;
    Page<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery, Pageable pageable);
    GetParticipationQueryResult getParticipation(String participationId) throws CommonException;
    void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException;
}
