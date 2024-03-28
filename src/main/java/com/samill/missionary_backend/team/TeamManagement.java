package com.samill.missionary_backend.team;

import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.team.board.domain.service.TeamBoardService;
import com.samill.missionary_backend.team.member.domain.dto.TeamMemberDto;
import com.samill.missionary_backend.team.member.domain.model.TeamMember;
import com.samill.missionary_backend.team.member.domain.service.TeamMemberService;
import com.samill.missionary_backend.team.team.domain.dto.TeamDto;
import com.samill.missionary_backend.team.team.domain.mapper.TeamMapper;
import com.samill.missionary_backend.team.team.domain.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class TeamManagement implements TeamExternalService {

    private final TeamBoardService teamBoardService;
    private final TeamService teamService;
    private final TeamMemberService teamMemberService;
    private final ParticipationExternalService participationExternalService;
    private final TeamMapper teamMapper;


    @Override
    public void createTeam() {

    }

    @Override
    public TeamDto getTeam(String teamId) {
        return this.teamMapper.teamToTeamDto(this.teamService.getTeamById(teamId));
    }

    @Override
    public void updateTeam(String teamId) {

    }

    @Override
    public void deleteTeam(String teamId) {

    }

    @Override
    public List<TeamDto> getTeams(String missionaryId) {
        return this.teamService.getTeamsByMissionaryId(missionaryId).stream()
                .map(this.teamMapper::teamToTeamDto)
                .toList();
    }

    @Override
    public void addTeamMember(String teamId, String userId) {
        final boolean isExistTeam = this.teamService.isExistTeam(teamId);

        if (!isExistTeam) {
            throw new IllegalArgumentException("존재하지 않는 팀입니다.");
        }

        final String missionaryId = this.teamService.getMissionaryIdByTeamId(teamId);

        final boolean isParticipant = this.participationExternalService.isParticipant(missionaryId, userId);

        if (!isParticipant) {
            throw new IllegalArgumentException("참가자가 아닙니다.");
        }

        this.teamMemberService.addTeamMember(teamId, userId);
    }

    @Override
    public void updateRole(String teamMemberId, String roleId) {
        this.teamMemberService.updateRole(teamMemberId, roleId);
    }

    @Override
    public void createBoard(String teamId, String memberId, String userId) {
        final TeamMember teamMember = this.teamMemberService.getTeamMember(teamId, userId);

        if (!teamMember.checkWriteableTeamBoard(teamId)) {
            throw new IllegalArgumentException("글을 작성할 권한이 없습니다.");
        }

        this.teamBoardService.writeBoard(teamId, memberId);
    }

    @Override
    public void createBoard(String teamId, String memberId) {
        this.teamBoardService.writeBoard(teamId, memberId);
    }

    @Override
    public boolean isTeamMember(String teamId, String userId) {
        return this.teamMemberService.isExistTeamMember(teamId, userId);
    }

    @Override
    public void addChurch() {

    }

    @Override
    public List<TeamMemberDto> getTeamMembers(String teamId) {
        return null;
    }
}
