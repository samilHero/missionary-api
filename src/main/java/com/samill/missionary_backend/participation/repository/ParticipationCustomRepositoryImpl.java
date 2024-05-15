package com.samill.missionary_backend.participation.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.participation.entity.QParticipation;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ParticipationCustomRepositoryImpl implements ParticipationCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    QParticipation participation = QParticipation.participation;
    @Override
    public List<GetParticipationQueryResult> findAllByConditionOrderByCreatedAtAsc(GetParticipationsQuery getParticipationsQuery) {
        return jpaQueryFactory
                .select(Projections.constructor(GetParticipationQueryResult.class,
                participation.id,
                participation.missionaryId,
                participation.userId,
                participation.name,
                participation.memberId,
                participation.applyFee,
                participation.isPaid,
                participation.identificationNumber,
                participation.createdAt))
                .from(participation)
                .where(
                        gtCreateTime(getParticipationsQuery.getCursorId()),
                        participation.missionaryId.eq(getParticipationsQuery.getMissionaryId())  //매번 사용되는 검색 조건
                ).orderBy(participation.createdAt.asc())
                .limit(getParticipationsQuery.getPageSize())
                .fetch();
    }

    private BooleanExpression gtCreateTime(OffsetDateTime cursorTime) {
        return cursorTime == null ? null : participation.createdAt.gt(cursorTime);
    }
}
