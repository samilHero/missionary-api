package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.participation.dto.ParticipationDto;

import java.awt.print.Pageable;
import java.util.List;

public interface ParticipationInternalService {
    void participate(ParticipationDto participateDto);
    void cancelParticipation(ParticipationDto participationDto);
    List<Pageable> getParticipationList();
    void updateParticipation(ParticipationDto participationDto);
    void updateDeposition(ParticipationDto participationDto);
}
