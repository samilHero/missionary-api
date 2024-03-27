package com.samill.missionary_backend.team;

public interface TeamExternalService {

    // 팀 crud
    void createTeam();

    //팀원 crd
    void addTeamMember();

    //팀내 권한 부여
    void addRole();

    //게시판 crud
    void createBoard();

    //팀원 인지
    boolean isTeamMember();

    //교회 연결
    void addChurch();
}
