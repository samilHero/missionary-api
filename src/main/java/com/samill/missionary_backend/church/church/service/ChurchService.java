package com.samill.missionary_backend.church.church.service;

import com.samill.missionary_backend.church.church.dto.CreateChurchRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchResult;
import com.samill.missionary_backend.church.church.dto.GetChurchesRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchesResult;
import com.samill.missionary_backend.church.church.dto.UpdateChurchRequest;
import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.church.church.exception.NotFoundChurchException;
import com.samill.missionary_backend.church.church.mapper.ChurchMapper;
import com.samill.missionary_backend.church.church.repository.ChurchRepository;
import com.samill.missionary_backend.common.exception.CommonException;
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

    public GetChurchResult getChurch(String id) throws CommonException {

        return ChurchMapper.INSTANCE.churchToGetChurchResult(
            churchRepository.findById(id)
                .orElseThrow(NotFoundChurchException::new)
        );
    }

    public String createChurch(@NonNull String memberId, @NonNull CreateChurchRequest createChurchRequest) {
        final Church church = churchRepository.save(ChurchMapper.INSTANCE.createChurchRequestToChurch(createChurchRequest));
        return church.getId();
    }

    public void deleteChurch(@NonNull String id, @NonNull String memberId) {
        churchRepository.deleteById(id);
    }


    public void updateChurch(@NonNull String id, @NonNull String memberId, @NonNull UpdateChurchRequest updateChurchRequest) throws CommonException {
        final Church church = churchRepository.findById(id)
            .orElseThrow(NotFoundChurchException::new);

        church.changeName(updateChurchRequest.name());
        church.changeAddress(updateChurchRequest.address());
        church.changePastor(updateChurchRequest.pastor());

    }


}
