package com.samill.missionary_backend.participation;

public interface ParticipationExternalService {

    boolean isParticipant(String missionaryId, String userId);

    void participate();

    void cancelParticipation();

    void updateParticipation();

    //참가 신청자 정보
    void getParticipant();

    //참가신청서 목록
    void getParticipations();

    // 확정자인지 확인
    boolean isConfirmedParticipant(String participantId);

    // 확정처리
    void confirm();

    // 확정처리
    void cancelConfirm();

    //입금 독촉장 보내기
    void sendDepositReminder();

}
