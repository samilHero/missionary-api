package com.samill.missionary_backend.participation.domain.service;

import com.samill.missionary_backend.participation.domain.dto.ParticipationDto;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface ParticipationService {
    void participate(ParticipationDto participateDto);
    void cancelParticipation(ParticipationDto participationDto);
    List<Pageable> getParticipationList();
    void updateParticipation(ParticipationDto participationDto);
    void updateDeposition(ParticipationDto participationDto);
}
