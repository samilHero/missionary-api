package com.samill.missionary_backend.accommodation;

public interface AccommodationExternalService {

    void createAccommodation();

    void allocationRoom();

    void deallocateRoom();

    boolean isPackedRoom();
}
