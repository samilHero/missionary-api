package com.samill.missionary_backend.church;

import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.common.exception.BaseException;

public interface ChurchExternalService {

    GetChurchesResult getChurches(GetChurchesRequest getChurchesRequest);

    GetChurchResult getChurch(String id) throws BaseException;

    void createChurch(String adminId, CreateChurchRequest createChurchRequest);

    void deleteChurch(String id, String memberId);

    void updateChurch(String id, String adminId, UpdateChurchRequest updateChurchRequest) throws BaseException;
}
