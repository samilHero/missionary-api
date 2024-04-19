package com.samill.missionary_backend.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BusManagement implements BusExternalService {

    @Override
    public void createBus(String busName, int maxCapacity) {

    }

    @Override
    public void allocationBus(String busId, String teamMemberId) {

    }

    @Override
    public void deallocateBus(String busId, String teamMemberId) {

    }
}
