package com.samill.missionary_backend.common.entity;

import jakarta.persistence.Embeddable;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@Embeddable
public class Period {

    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    Boolean inPeriod(OffsetDateTime target) {
        final Boolean isEqualOrAfterThenStartDate = target.isEqual(startDate) || target.isAfter(startDate);
        final Boolean isBeforeThenAfterDate = target.isBefore(endDate);
        return isEqualOrAfterThenStartDate && isBeforeThenAfterDate;
    }


}
