package com.samill.missionary_backend.church.dto;

import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;

public record UpdateChurchCommand(
    String name,
    String pastorName,
    String pastorPhone,
    String addressBasic,
    String addressDetail

) {


    public Address address() {
        return new Address(addressBasic, addressDetail);
    }

    public Pastor pastor() {
        return new Pastor(pastorName, pastorPhone);
    }

}
