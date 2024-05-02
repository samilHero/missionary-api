package com.samill.missionary_backend.participation.service;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.CreateParticipationDto;
import com.samill.missionary_backend.participation.dto.UpdateParticipationDto;
import com.samill.missionary_backend.participation.entity.Participation;

import java.util.List;

public interface ParticipationService {
    void participate(CreateParticipationDto createParticipationDto, int maxCount) throws CommonException;
    void cancelParticipation(UpdateParticipationDto updateParticipationDto);
    List<Participation> getParticipations(String missionaryId);
    void updateParticipation(UpdateParticipationDto updateParticipationDto);
}
