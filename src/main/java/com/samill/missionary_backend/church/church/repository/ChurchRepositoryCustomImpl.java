package com.samill.missionary_backend.church.church.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChurchRepositoryCustomImpl implements ChurchRepositoryCustom {

    private final JPAQueryFactory queryFactory;


}
