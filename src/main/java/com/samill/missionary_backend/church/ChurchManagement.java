package com.samill.missionary_backend.church;


import com.samill.missionary_backend.church.church.dto.CreateChurchRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchResult;
import com.samill.missionary_backend.church.church.dto.GetChurchesRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchesResult;
import com.samill.missionary_backend.church.church.dto.UpdateChurchRequest;
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
    public GetChurchesResult getChurches(
        GetChurchesRequest getChurchesRequest
    ) {
        return churchService.getChurches(getChurchesRequest);
    }

    @Override
    public GetChurchResult getChurch(@NonNull String id) throws CommonException {
        return churchService.getChurch(id);
    }

    @Transactional
    @Override
    public void createChurch(String adminId, @NonNull CreateChurchRequest createChurchRequest) {
        churchService.createChurch(adminId, createChurchRequest);
    }

    @Transactional
    @Override
    public void deleteChurch(String id, String memberId) {
        churchService.deleteChurch(id, memberId);
    }

    @Transactional
    @Override
    public void updateChurch(@NonNull String id, @NonNull String adminId, @NonNull UpdateChurchRequest updateChurchRequest) throws CommonException {
        churchService.updateChurch(id, adminId, updateChurchRequest);
    }
}
