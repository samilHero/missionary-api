package com.samill.missionary_backend.missionary.participation.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import com.samill.missionary_backend.missionary.participation.entity.QParticipation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ParticipationCustomRepositoryImpl implements ParticipationCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    QParticipation participation = QParticipation.participation;

    @Override
    public Page<GetParticipationQueryResult> findAllByQuery(String missionaryId, GetParticipationsQuery getParticipationsQuery, Pageable pageable) {
        List<GetParticipationQueryResult> content = jpaQueryFactory
                .select(Projections.constructor(GetParticipationQueryResult.class,
                    participation.id,
                    participation.missionaryId,
                        participation.userId,
                        participation.name,
                        participation.memberId,
                        participation.birthDate,
                        participation.applyFee,
                        participation.isPaid,
                        participation.identificationNumber,
                        participation.isOwnCar,
                        participation.createdAt))
                .from(participation)
                .where(
                        participation.missionaryId.eq(missionaryId),
                        isPaid(getParticipationsQuery.isPaid()))
                .orderBy(participation.createdAt.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(participation.count())
                .from(participation)
                .where(participation.missionaryId.eq(missionaryId))
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

//    @Override
//    public List<GetParticipationQueryResult> findAllByConditionOrderByCreatedAtAsc(GetParticipationsQuery getParticipationsQuery) {
//        return jpaQueryFactory
//                .select(Projections.constructor(GetParticipationQueryResult.class,
//                participation.id,
//                participation.missionaryId,
//                participation.userId,
//                participation.name,
//                participation.memberId,
//                participation.applyFee,
//                participation.isPaid,
//                participation.identificationNumber,
//                participation.isOwnCar,
//                participation.createdAt))
//                .from(participation)
//                .where(
//                        gtCreateTime(getParticipationsQuery.getCursorId()),
//                        participation.missionaryId.eq(getParticipationsQuery.getMissionaryId())  //매번 사용되는 검색 조건
//                ).orderBy(participation.createdAt.asc())
//                .limit(getParticipationsQuery.getPageSize())
//                .fetch();
//    }
//
    private BooleanExpression isPaid(Boolean isPaid) {
        return isPaid == null ? null : participation.isPaid.eq(isPaid);
    }
}
