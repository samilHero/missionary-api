package com.samill.missionary_backend.team.member.domain.service;

import com.samill.missionary_backend.team.member.domain.model.TeamMember;

import java.util.List;

public interface TeamMemberService {

    TeamMember addTeamMember(String teamId, String userId);


    TeamMember getTeamMember(String teamMemberId);

    TeamMember getTeamMember(String teamId, String userId);

    boolean isExistTeamMember(String teamMemberId);

    boolean isExistTeamMember(String teamId, String userId);

    List<TeamMember> getTeamMembers(String teamId);

    void updateRole(String teamMemberId, String roleId);
}
