package com.samill.missionary_backend.church.church.dto;

public record GetChurchResult(
        String id,
        String name,
        String pastorName,
        String pastorPhone,
        String address,

        String visitPurpose

) {

}
