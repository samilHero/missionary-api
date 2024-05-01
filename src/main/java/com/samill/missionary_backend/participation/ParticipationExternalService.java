package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.participation.dto.CreateParticipationDto;
import com.samill.missionary_backend.participation.dto.GetParticipationsDto;
import com.samill.missionary_backend.participation.dto.UpdateParticipationDto;
import org.springframework.stereotype.Service;

@Service
public interface ParticipationExternalService {
    void participate(CreateParticipationDto createParticipationDto) throws CommonException;
    void cancelParticipation(UpdateParticipationDto updateParticipationDto);
    GetParticipationsDto getParticipations(String missionaryId);
}
