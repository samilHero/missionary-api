package com.samill.missionary_backend.missionary.team.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samill.missionary_backend.missionary.participation.entity.QParticipation;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQuery;
import com.samill.missionary_backend.missionary.team.dto.GetTeamListQueryResult;
import com.samill.missionary_backend.missionary.team.entity.QTeam;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamCustomRepositoryImpl implements TeamCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private QTeam team = QTeam.team;
    private QParticipation participation = QParticipation.participation;

    @Override
    public List<GetTeamListQueryResult> findAllByQuery(String missionaryId, GetTeamListQuery getTeamListQuery) {
        List<GetTeamListQueryResult> list = jpaQueryFactory
            .select(Projections.constructor(GetTeamListQueryResult.class,
                team.id,
                team.missionaryId,
                team.churchId,
                team.churchName,
                team.leaderUserId,
                team.leaderUserName,
                participation.id.countDistinct(),
                participation.isPaid.eq(true).countDistinct()
            ))
            .from(team)
            .leftJoin(participation.team)
            .where(
                team.missionaryId.eq(missionaryId),
                isChurchId(getTeamListQuery.churchId()),
                isLeaderUserId(getTeamListQuery.leaderUserId())
            )
            .fetch();
        return list;
    }

    private BooleanExpression isChurchId(String churchId) {
        return churchId == null ? null : team.churchId.eq(churchId);
    }

    private BooleanExpression isLeaderUserId(String leaderUserId) {
        return leaderUserId == null ? null : team.churchId.eq(leaderUserId);
    }
}
