package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ParticipationExternalService {
    void createParticipation(CreateParticipationCommand createParticipationDto) throws CommonException;
    void deleteParticipation(DeleteParticipationCommand deleteParticipationCommand) throws CommonException;
    Page<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery, Pageable pageable);
    GetParticipationQueryResult getParticipation(String participationId) throws CommonException;
    void updateParticipation(UpdateParticipationCommand updateParticipationCommand) throws CommonException;
}
