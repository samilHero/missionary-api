package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipationExternalService {
    void createParticipation(CreateParticipationCommand createParticipationDto) throws CommonException;
    void deleteParticipation(DeleteParticipationCommand deleteParticipationCommand) throws CommonException;
    List<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery);
    GetParticipationQueryResult getParticipation(String participationId) throws CommonException;
    void updateParticipation(UpdateParticipationCommand updateParticipationCommand) throws CommonException;
}
