package com.samill.missionary_backend.missionary.participation.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samill.missionary_backend.missionary.church.entity.QMissionaryChurch;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import com.samill.missionary_backend.missionary.dto.GetParticipationsDownloadQuery;
import com.samill.missionary_backend.missionary.dto.GetParticipationsQuery;
import com.samill.missionary_backend.missionary.participation.entity.QParticipation;
import com.samill.missionary_backend.missionary.team.entity.QTeam;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ParticipationCustomRepositoryImpl implements ParticipationCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QParticipation participation = QParticipation.participation;
    private final QTeam team = QTeam.team;
    private final QMissionaryChurch missionaryChurch = QMissionaryChurch.missionaryChurch;

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
                missionaryChurch.name,
                team.leaderUserName,
                participation.applyFee,
                participation.isPaid,
                participation.identificationNumber,
                participation.isOwnCar,
                participation.createdAt))
            .from(participation)
            .leftJoin(participation.team, team)
            .innerJoin(missionaryChurch).on(missionaryChurch.id.eq(team.churchId))
            .where(
                participation.missionaryId.eq(missionaryId),
                isPaid(getParticipationsQuery.isPaid()),
                isParticipantName(getParticipationsQuery.name()),
                betweenCreateDate(getParticipationsQuery.fromDate(), getParticipationsQuery.endDate()))
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

    @Override
    public List<GetParticipationQueryResult> findAllByQueryForCsv(String missionaryId,
        GetParticipationsDownloadQuery getParticipationsDownloadQuery) {
        List<GetParticipationQueryResult> list = jpaQueryFactory
            .select(Projections.constructor(GetParticipationQueryResult.class,
                participation.id,
                participation.missionaryId,
                participation.userId,
                participation.name,
                participation.memberId,
                participation.birthDate,
                missionaryChurch.name,
                team.leaderUserName,
                participation.applyFee,
                participation.isPaid,
                participation.identificationNumber,
                participation.isOwnCar,
                participation.createdAt))
            .from(participation)
            .leftJoin(participation.team, team)
            .innerJoin(missionaryChurch).on(missionaryChurch.id.eq(team.churchId))
            .where(
                participation.missionaryId.eq(missionaryId),
                isPaid(getParticipationsDownloadQuery.isPaid()),
                betweenCreateDate(getParticipationsDownloadQuery.fromDate(), getParticipationsDownloadQuery.endDate()),
                isParticipantName(getParticipationsDownloadQuery.name()))
            .orderBy(participation.createdAt.asc())
            .fetch();

        return list;
    }

    private BooleanExpression isPaid(Boolean isPaid) {
        return isPaid == null ? null : participation.isPaid.eq(isPaid);
    }

    private BooleanExpression betweenCreateDate(String fromDate, String endDate) {
        return fromDate == null ? null : participation.createdAt.between(OffsetDateTime.parse(fromDate), OffsetDateTime.parse(endDate));
    }

    private BooleanExpression isParticipantName(String name) {
        return name == null ? null : participation.name.eq(name);
    }
}
