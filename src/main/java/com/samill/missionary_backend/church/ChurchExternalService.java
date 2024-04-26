package com.samill.missionary_backend.church;

import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.common.exception.CommonException;

public interface ChurchExternalService {

    GetChurchesQueryResult getChurches(GetChurchesQuery getChurchesQuery);

    GetChurchQueryResult getChurch(String id) throws CommonException;

    void createChurch(String adminId, CreateChurchCommand createChurchCommand);

    void deleteChurch(String id, String memberId);

    void updateChurch(String id, String adminId, UpdateChurchCommand updateChurchCommand) throws CommonException;
}
