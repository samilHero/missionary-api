package com.samill.missionary_backend.participation.infrastructure.service;

import com.samill.missionary_backend.participation.domain.dto.ParticipationDto;
import com.samill.missionary_backend.participation.domain.service.ParticipationService;
import com.samill.missionary_backend.participation.infrastructure.entity.Participation;
import com.samill.missionary_backend.participation.infrastructure.mapper.ParticipationMapper;
import com.samill.missionary_backend.participation.infrastructure.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationMapper participationMapper;
    private final ParticipationRepository participationRepository;

    @Override
    public void participate(ParticipationDto participateDto) {
        Participation participation = participationMapper.INSTANCE.dtoToEntity(participateDto);
        Participation response = participationRepository.save(participation);
        System.out.println(response.getApplyFee());
    }

    @Override
    public void cancelParticipation(ParticipationDto participationDto) {

    }

    @Override
    public List<Pageable> getParticipationList() {
        return null;
    }

    @Override
    public void updateParticipation(ParticipationDto participationDto) {

    }

    @Override
    public void updateDeposition(ParticipationDto participationDto) {

    }
}
