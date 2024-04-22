package com.samill.missionary_backend.church.church.service;

import com.samill.missionary_backend.church.church.dto.*;
import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.church.church.exception.ChurchNotFoundException;
import com.samill.missionary_backend.church.church.mapper.ChurchMapper;
import com.samill.missionary_backend.church.church.repository.ChurchRepository;
import com.samill.missionary_backend.common.exception.BaseException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChurchService {

    private final ChurchRepository churchRepository;

    public GetChurchesResult getChurches(@NonNull GetChurchesRequest request) {

        final PageRequest pageRequest = PageRequest.of(
                0,
                request.pageSize()
        );

        final Slice<Church> churches = this.churchRepository.findAllWithCursor(request.cursor(), pageRequest);


        return ChurchMapper.INSTANCE.churchesToGetChurchesResult(
                churches.toList(),
                churches.hasNext()
        );

    }

    public GetChurchResult getChurch(String id) throws BaseException {

        return ChurchMapper.INSTANCE.churchToGetChurchResult(
                churchRepository.findById(id)
                        .orElseThrow(ChurchNotFoundException::new)
        );
    }

    public void createChurch(@NonNull String adminId, @NonNull CreateChurchRequest createChurchRequest) {
        churchRepository.save(ChurchMapper.INSTANCE.createChurchRequestToChurch(createChurchRequest));
    }

    public void deleteChurch(@NonNull String id, @NonNull String memberId) {
        churchRepository.deleteById(id);
    }


    public void updateChurch(@NonNull String id, @NonNull String adminId, @NonNull UpdateChurchRequest updateChurchRequest) throws BaseException {
        churchRepository.findById(id)
                .orElseThrow(ChurchNotFoundException::new)
                .update(updateChurchRequest);
    }

}
