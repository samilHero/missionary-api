package com.samill.missionary_backend.church;


import com.samill.missionary_backend.church.dto.CreateChurchDto;
import com.samill.missionary_backend.church.dto.GetChurchDto;
import com.samill.missionary_backend.church.dto.GetChurchesDto;
import com.samill.missionary_backend.church.dto.UpdateChurchDto;
import com.samill.missionary_backend.church.service.ChurchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ChurchManagement implements ChurchExternalService {

    private final ChurchService churchService;

    @Override
    public GetChurchesDto getChurches() {
        return null;
    }

    @Override
    public GetChurchDto getChurch(String id) {
        return null;
    }

    @Override
    public void addChurch(String adminId, CreateChurchDto createChurchDto) {

    }

    @Override
    public void removeChurch(String id, String adminId) {

    }

    @Override
    public void updateChurch(String id, String adminId, UpdateChurchDto updateChurchDto) {

    }
}
