package com.samill.missionary_backend.church.repository;

import static com.samill.missionary_backend.church.entity.QChurch.church;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samill.missionary_backend.church.entity.Church;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChurchRepositoryCustomImpl implements ChurchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Church> findAllWithCursorWithPageSize(String cursor, Integer pageSize) {

        return queryFactory.selectFrom(church)
            .where(
                cursor == null ? null :
                    church.name.gt(cursor)
            )
            .orderBy(
                church.name.asc(),
                church.createdAt.asc()
            )
            .limit(pageSize).fetch();
    }

}
