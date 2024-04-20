package com.samill.missionary_backend.church.church.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samill.missionary_backend.church.church.dto.GetChurchesCursor;
import com.samill.missionary_backend.church.church.entity.Church;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.samill.missionary_backend.church.church.entity.QChurch.church;

@RequiredArgsConstructor
public class ChurchRepositoryCustomImpl implements ChurchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Church> findAllWithCursor(GetChurchesCursor cursor, @NonNull Integer pageSize) {
        return queryFactory.selectFrom(church)
                .where(findAllWithCursor(cursor))
                .orderBy(
                        church.name.asc(),
                        church.id.asc()
                )
                .limit(pageSize).fetch();
    }


    private BooleanExpression findAllWithCursor(GetChurchesCursor cursor) {
        if (cursor == null) {
            return null;
        }

        final StringTemplate nameTemplate = Expressions.stringTemplate("{0}", church.name);
//
        return StringExpressions.rpad(nameTemplate, 20, cursor.getCharPad()).concat(cursor.id()).gt(cursor.value());

    }
}
