package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.*;

import java.util.List;

public interface ParticipationService {
    void createParticipation(CreateParticipationCommand createParticipationDto, int maxCount) throws CommonException;
    void deleteParticipation(DeleteParticipationCommand deleteParticipationCommand) throws CommonException;
    void updateParticipation(UpdateParticipationCommand updateParticipationCommand) throws CommonException;
    List<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery);
    GetParticipationQueryResult getParticipation(String participationId) throws CommonException;
}
