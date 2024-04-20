package com.samill.missionary_backend.church;

import com.samill.missionary_backend.church.dto.CreateChurchDto;
import com.samill.missionary_backend.church.dto.GetChurchDto;
import com.samill.missionary_backend.church.dto.GetChurchesDto;
import com.samill.missionary_backend.church.dto.UpdateChurchDto;

public interface ChurchExternalService {

    GetChurchesDto getChurches();

    GetChurchDto getChurch(String id);

    void addChurch(String adminId, CreateChurchDto createChurchDto);

    void removeChurch(String id, String adminId);

    void updateChurch(String id, String adminId, UpdateChurchDto updateChurchDto);
}
