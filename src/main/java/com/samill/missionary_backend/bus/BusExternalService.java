package com.samill.missionary_backend.bus;

public interface BusExternalService {
    void createBus(

            String busName,
            int maxCapacity
            );

    void allocationBus(
            String busId,
            String teamMemberId
    );

    void deallocateBus(
            String busId,
            String teamMemberId
    );
}
