package com.samill.missionary_backend.church.church.dto;

public record UpdateChurchRequest(
        String name,
        String pastorName,
        String pastorPhone,
        String ministry,
        String addressBasic,
        String addressDetail,
        String visitPurpose

) {

}
