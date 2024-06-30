package com.samill.missionary_backend.missionary.missionary.entity;

import com.samill.missionary_backend.common.entity.Period;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class MissionaryDetail {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startDate", column = @Column(name = "participation_start_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "participation_end_date")),
    })
    private Period participationPeriod;

    private Integer price;

    private String description;

    private Integer maximumParticipantCount;

    @Builder.Default
    private Integer currentParticipantCount = 0;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "bankName", column = @Column(name = "bank_account_bank_name")),
        @AttributeOverride(name = "placeHolder", column = @Column(name = "bank_account_place_holder")),
        @AttributeOverride(name = "number", column = @Column(name = "bank_account_number")),
    })
    private BankAccount bankAccount;

    public boolean isParticipationPeriod(OffsetDateTime date) {
        return participationPeriod.inPeriod(date);
    }


}
