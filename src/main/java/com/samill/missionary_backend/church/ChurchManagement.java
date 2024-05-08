package com.samill.missionary_backend.church;


import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.church.church.service.ChurchService;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ChurchManagement implements ChurchExternalService {

    private final ChurchService churchService;
    private final MemberExternalService memberExternalService;

    @Override
    public GetChurchesQueryResult getChurches(
    ) {
        return churchService.getChurches();
    }

    @Override
    public GetChurchQueryResult getChurch(@NonNull String id) throws CommonException {
        return churchService.getChurch(id);
    }

    @Transactional
    @Override
    public CreateChurchCommandResult createChurch(@NonNull CreateChurchCommand createChurchCommand) {
        final String churchId = churchService.createChurch(createChurchCommand);
        return new CreateChurchCommandResult(churchId);
    }

    @Transactional
    @Override
    public void deleteChurch(String id) {
        churchService.deleteChurch(id);
    }

    @Transactional
    @Override
    public void updateChurch(@NonNull String id, @NonNull UpdateChurchCommand updateChurchCommand) throws CommonException {
        churchService.updateChurch(id, updateChurchCommand);
    }
}
