package com.samill.missionary_backend.missionary.missionary.mapper;

import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.missionary.missionary.entity.BankAccount;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.entity.MissionaryDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import org.springframework.jdbc.core.RowMapper;

public class MissionaryRowMapper implements RowMapper<Missionary> {

    @Override
    public Missionary mapRow(ResultSet rs, int rowNum) throws SQLException {
        final var mapRet = new HashMap<String, String>();

        return Missionary.builder()
            .id(rs.getString("id"))
            .name(rs.getString("name"))
            .period(Period.builder()
                .startDate(getOffsetDateTime(rs, "start_date"))
                .endDate(getOffsetDateTime(rs, "end_date"))
                .build())
//            .region(MissionaryRegion.keyOf(rs.getString("region").trim()))
            .detail(
                MissionaryDetail.builder()
                    .participationPeriod(
                        Period.builder()
                            .startDate(getOffsetDateTime(rs, "participation_start_date"))
                            .endDate(getOffsetDateTime(rs, "participation_end_date"))
                            .build()
                    )
                    .price(rs.getInt("price"))
                    .description(rs.getString("description"))
                    .maximumParticipantCount(rs.getInt("maximum_participant_count"))
                    .bankAccount(
                        BankAccount.builder()
                            .bankName(rs.getString("bank_account_bank_name"))
                            .placeHolder(rs.getString("bank_account_place_holder"))
                            .number(rs.getString("bank_account_number"))
                            .build()
                    )
                    .build()
            )
            .createdAt(rs.getTimestamp("created_at").toInstant().atOffset(ZoneOffset.UTC))
            .build();
    }

    private OffsetDateTime getOffsetDateTime(ResultSet rs, String columnName) throws SQLException {
        final var timestamp = rs.getTimestamp(columnName);

        if (timestamp == null) {
            return null;
        }

        return rs.getTimestamp(columnName).toInstant().atOffset(ZoneOffset.UTC);
    }

}
