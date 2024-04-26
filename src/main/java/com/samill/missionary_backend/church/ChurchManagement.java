package com.samill.missionary_backend.church;


import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.church.church.service.ChurchService;
import com.samill.missionary_backend.common.exception.CommonException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ChurchManagement implements ChurchExternalService {

    private final ChurchService churchService;

    @Override
    public GetChurchesQueryResult getChurches(
            GetChurchesQuery getChurchesQuery
    ) {
        return churchService.getChurches(getChurchesQuery);
    }

    @Override
    public GetChurchQueryResult getChurch(@NonNull String id) throws CommonException {
        return churchService.getChurch(id);
    }

    @Transactional
    @Override
    public void createChurch(String adminId, @NonNull CreateChurchCommand createChurchCommand) {
        churchService.createChurch(adminId, createChurchCommand);
    }

    @Transactional
    @Override
    public void deleteChurch(String id, String memberId) {
        churchService.deleteChurch(id, memberId);
    }

    @Transactional
    @Override
    public void updateChurch(@NonNull String id, @NonNull String adminId, @NonNull UpdateChurchCommand updateChurchCommand) throws CommonException {
        churchService.updateChurch(id, adminId, updateChurchCommand);
    }
}
