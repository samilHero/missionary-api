package com.samill.missionary_backend.missionary.church.service;

import com.samill.missionary_backend.missionary.church.repository.MissionaryMissionaryChurchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryChurchService {

    private final MissionaryMissionaryChurchRepository churchRepository;

//    public GetMissionaryChurchesResult getChurches(@NonNull GetMissionaryChurchesRequest request) {
//
//        final PageRequest pageRequest = PageRequest.of(
//            0,
//            request.pageSize()
//        );
//
//        final Slice<Church> churches = this.churchRepository.findAllWithCursor(request.cursor(), pageRequest);
//
//        return ChurchMapper.INSTANCE.churchesToGetChurchesResult(
//            churches.toList(),
//            churches.hasNext()
//        );
//
//    }
//
//    public GetMissionaryChurchResult getChurch(String lastChruchId) throws CommonException {
//
//        return ChurchMapper.INSTANCE.churchToGetChurchResult(
//            churchRepository.findById(lastChruchId)
//                .orElseThrow(NotFoundChurchException::new)
//        );
//    }
//
//    public String createChurch(@NonNull String memberId, @NonNull CreateMissionaryChurchRequest createChurchRequest) {
//        final Church church = churchRepository.save(ChurchMapper.INSTANCE.createChurchRequestToChurch(createChurchRequest));
//        return church.getId();
//    }
//
//    public void deleteChurch(@NonNull String lastChruchId, @NonNull String memberId) {
//        churchRepository.deleteById(lastChruchId);
//    }
//
//
//    public void updateChurch(@NonNull String lastChruchId, @NonNull String memberId, @NonNull UpdateChurchRequest updateChurchRequest) throws CommonException {
//        final Church church = churchRepository.findById(lastChruchId)
//            .orElseThrow(NotFoundChurchException::new);
//
//        church.changeName(updateChurchRequest.name());
//        church.changeAddress(updateChurchRequest.address());
//        church.changePastor(updateChurchRequest.pastor());
//
//    }


}
