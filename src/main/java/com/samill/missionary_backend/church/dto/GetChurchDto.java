package com.samill.missionary_backend.church.dto;

public record GetChurchDto(
    String id,
    String name,
    String pastorName,
    String pastorPhone,
    String address,

    String visitPurpose

) {

}
