package com.samill.missionary_backend.participation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParticipationManagement implements ParticipationExternalService {

    @Override
    public boolean isParticipant(String missionaryId, String userId) {
        return false;
    }

    @Override
    public void participate() {

    }

    @Override
    public void cancelParticipation() {

    }

    @Override
    public void updateParticipation() {

    }

    @Override
    public void getParticipant() {

    }

    @Override
    public void getParticipations() {

    }

    @Override
    public boolean isConfirmedParticipant(String participantId) {
        return false;
    }

    @Override
    public void confirm() {

    }

    @Override
    public void cancelConfirm() {

    }

    @Override
    public void sendDepositReminder() {

    }
}
