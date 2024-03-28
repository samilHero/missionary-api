package com.samill.missionary_backend.team;

import com.samill.missionary_backend.team.member.domain.dto.TeamMemberDto;
import com.samill.missionary_backend.team.team.domain.dto.TeamDto;

import java.util.List;

public interface TeamExternalService {

    // 팀 crud
    void createTeam();

    TeamDto getTeam(String teamId);

    void updateTeam(String teamId);

    void deleteTeam(String teamId);

    List<TeamDto> getTeams(String missionaryId);

    //팀원 crd
    void addTeamMember(String teamId, String userId);

    //팀내 권한 부여
    void updateRole(String memberId, String roleId);

    // 게시글 생성 By user
    void createBoard(String teamId, String memberId, String userId);

    // 게시글 생성 By admin
    void createBoard(String memberId, String teamId);

    //팀원 인지
    boolean isTeamMember(String teamId, String userId);

    //교회 연결
    void addChurch();

    List<TeamMemberDto> getTeamMembers(String teamId);
}
