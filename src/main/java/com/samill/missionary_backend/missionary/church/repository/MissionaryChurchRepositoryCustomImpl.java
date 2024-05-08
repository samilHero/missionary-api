package com.samill.missionary_backend.missionary.church.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samill.missionary_backend.missionary.church.entity.MissionaryChurch;
import com.samill.missionary_backend.missionary.church.entity.MissionaryChurches;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import static com.samill.missionary_backend.missionary.church.entity.QMissionaryChurch.missionaryChurch;

@RequiredArgsConstructor
public class MissionaryChurchRepositoryCustomImpl implements MissionaryChurchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<MissionaryChurch> findAllWithCursor(String missionaryId, @NonNull Pageable pageable) {

        final MissionaryChurches churches = new MissionaryChurches(
                queryFactory.selectFrom(missionaryChurch)
                        .where(missionaryChurch.missionary.id.eq(missionaryId))
                        .orderBy(
                                missionaryChurch.name.asc(),
                                missionaryChurch.id.asc()
                        )
                        .limit(pageable.getPageSize()).fetch()

        );

        return new SliceImpl<>(churches.getValues(), pageable, churches.hasNext(pageable.getPageSize()));
    }


}
