package com.samill.missionary_backend.missionary.participation.service;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.CreateParticipationCommand;
import com.samill.missionary_backend.missionary.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsDownloadQuery;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateParticipationCommand;
import com.samill.missionary_backend.missionary.participation.entity.Participation;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParticipationService {

    void createParticipation(CreateParticipationCommand createParticipationDto, int maxCount) throws CommonException;

    void deleteParticipation(String participationId, DeleteParticipationCommand deleteParticipationCommand) throws CommonException;

    void updateParticipation(String participationId, UpdateParticipationCommand updateParticipationCommand) throws CommonException;

    Page<GetParticipationQueryResult> getParticipations(String missionaryId, GetParticipationsQuery getParticipationsQuery, Pageable pageable);

    Participation getParticipation(String participationId) throws CommonException;

    void updateParticipationPrivacyInfo(List<String> list);

    void updateParticipationPaid(List<String> ids);

    boolean isParticipating(@NonNull String missionaryId, @NonNull String userId);

    List<GetParticipationQueryResult> getParticipationsForDownload(@NonNull String missionaryId,
        GetParticipationsDownloadQuery getParticipationsDownloadQuery);
}
