package com.samill.missionary_backend.missionary.missionary.repository;

import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.mapper.MissionaryRowMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class MissionaryRepositoryCustomImpl implements MissionaryRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Missionary> findLatestMissionariesByRegion() {
        final var sql = "SELECT m.*, mr.* "
            + "FROM ( "
            + "    SELECT *, RANK() OVER (PARTITION BY missionary_region_id ORDER BY created_at) as rank "
            + "    FROM missionary "
            + "     ) as m "
            + "LEFT JOIN missionary_region mr "
            + "on m.missionary_region_id = mr.id "
            + "where m.rank = 1;";

        return jdbcTemplate.query(sql, new MissionaryRowMapper());
    }

    @Override
    public List<Missionary> findLatestMissionariesByRegion(String userId) {
//        final var sql = "SELECT * FROM ( " +
//            "    SELECT *, RANK() OVER(PARTITION BY region ORDER BY created_at DESC) as rank " +
//            "    FROM missionary " +
//            ") AS missionary " +
//            "LEFT JOIN missionary_staff " +
//            "   on missionary_staff.missionary_id = missionary.id " +
//            "WHERE missionary.rank = 1 AND missionary_staff.user_id = " + userId;
//
//        return jdbcTemplate.query(sql, new MissionaryRowMapper());

        return List.of();
    }


}
