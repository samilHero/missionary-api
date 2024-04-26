package com.samill.missionary_backend.church;

import com.samill.missionary_backend.church.church.dto.CreateChurchRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchResult;
import com.samill.missionary_backend.church.church.dto.GetChurchesRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchesResult;
import com.samill.missionary_backend.church.church.dto.UpdateChurchRequest;
import com.samill.missionary_backend.common.exception.CommonException;

public interface ChurchExternalService {

    GetChurchesResult getChurches(GetChurchesRequest getChurchesRequest);

    GetChurchResult getChurch(String id) throws CommonException;

    void createChurch(String adminId, CreateChurchRequest createChurchRequest);

    void deleteChurch(String id, String memberId);

    void updateChurch(String id, String adminId, UpdateChurchRequest updateChurchRequest) throws CommonException;
}
