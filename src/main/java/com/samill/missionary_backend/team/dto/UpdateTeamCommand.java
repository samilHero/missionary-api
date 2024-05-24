package com.samill.missionary_backend.team.dto;

public record UpdateTeamCommand (
        String leaderUserId,
        String teamName,
        String churchId) {
}
