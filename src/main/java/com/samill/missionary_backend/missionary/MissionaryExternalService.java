package com.samill.missionary_backend.missionary;

public interface MissionaryExternalService {

    //crud 기본
    void createMissionary();

    // 참가신청 가능한지
    boolean canParticipate();

    //일정 crud
    void createSchedule();

    //게시판 crud
    void createBoard();

    void addBus();

    void removeBus();

    void addChurch();

    void removeChurch();

    void addAccommodation();

    void removeAccommodation();

}
