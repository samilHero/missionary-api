package com.samill.missionary_backend.missionary.church.dto;

import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;
import lombok.NonNull;

public record UpdateChurchRequest(

    @NonNull String id,
    @NonNull String name,
    @NonNull String pastorName,
    @NonNull String pastorPhone,
    @NonNull String addressBasic,
    @NonNull String addressDetail,

    @NonNull String visitPurpose

) {


    public Address address() {
        return new Address(addressBasic, addressDetail);
    }

    public Pastor pastor() {
        return new Pastor(pastorName, pastorPhone);
    }

}
