package com.samill.missionary_backend.participation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ParticipantDto {
    private String id;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private LocalDate birthday;
}
