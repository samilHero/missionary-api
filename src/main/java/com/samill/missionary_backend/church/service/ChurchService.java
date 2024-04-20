package com.samill.missionary_backend.church.service;

import com.samill.missionary_backend.church.repository.ChurchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChurchService {

    private final ChurchRepository churchRepository;

//    GetChurchesDto getChurches() {
//
//        this.churchRepository.findChurchesBy(PageRequest.of);
//
//    }

}
