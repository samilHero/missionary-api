package com.samill.application.accommodation;

public interface AccommodationService {
    Accommodation createAccommodation(Accommodation accommodation);
    Accommodation getAccommodation(Long accommodationId);
    Accommodation updateAccommodation(Accommodation accommodation);
    void deleteAccommodation(Long accommodationId);
}
