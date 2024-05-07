package com.samill.missionary_backend.church;

import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.common.exception.CommonException;

public interface ChurchExternalService {

    GetChurchesQueryResult getChurches();

    GetChurchQueryResult getChurch(String id) throws CommonException;

    CreateChurchCommandResult createChurch(CreateChurchCommand createChurchCommand);

    void deleteChurch(String id);

    void updateChurch(String id, UpdateChurchCommand updateChurchCommand) throws CommonException;
}
