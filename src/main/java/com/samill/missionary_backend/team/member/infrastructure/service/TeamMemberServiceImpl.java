package com.samill.missionary_backend.team.member.infrastructure.service;

import com.samill.missionary_backend.team.member.domain.model.TeamMember;
import com.samill.missionary_backend.team.member.domain.repository.TeamMemberRepository;
import com.samill.missionary_backend.team.member.domain.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public TeamMember addTeamMember(String teamId, String userId) {
        return null;
    }

    @Override
    public TeamMember getTeamMember(String teamMemberId) {
        return null;
    }

    @Override
    public TeamMember getTeamMember(String teamId, String userId) {
        return null;
    }

    @Override
    public boolean isExistTeamMember(String teamMemberId) {
        return false;
    }

    @Override
    public boolean isExistTeamMember(String teamId, String userId) {
        return false;
    }
}
