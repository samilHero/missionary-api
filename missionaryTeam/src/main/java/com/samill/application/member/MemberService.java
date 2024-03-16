package com.samill.application.member;

import com.samill.application.team.Team;

import java.util.List;

public interface MemberService {
    Member addTeamMember(String userId, Team team);
    void deleteTeamMember(String userId, Team team);
    List<Member> getTeamMemberList(Long teamId);
}
