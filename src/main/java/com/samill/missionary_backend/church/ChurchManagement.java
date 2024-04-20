package com.samill.missionary_backend.church;


import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.church.church.service.ChurchService;
import com.samill.missionary_backend.common.exception.BaseException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public GetChurchResult getChurch(@NonNull String id) throws BaseException {
        return churchService.getChurch(id);
    }

    @Override
    public void createChurch(String adminId, @NonNull CreateChurchRequest createChurchRequest) {
        churchService.createChurch(adminId, createChurchRequest);
    }

    @Override
    public void deleteChurch(String id, String memberId) {
        churchService.deleteChurch(id, memberId);

    }

    @Override
    public void updateChurch(@NonNull String id, @NonNull String adminId, @NonNull UpdateChurchRequest updateChurchRequest) throws BaseException {
        churchService.updateChurch(id, adminId, updateChurchRequest);
    }
}
