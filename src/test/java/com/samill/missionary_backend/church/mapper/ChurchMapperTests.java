package com.samill.missionary_backend.church.mapper;

import com.samill.missionary_backend.church.dto.GetChurchDto;
import com.samill.missionary_backend.church.entity.Church;
import com.samill.missionary_backend.church.entity.VisitPurposeType;
import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChurchMapperTests {

    @Test
    @DisplayName("Church to GetChurchDto Test")
    void churchToGetChurchDtoTest() {
        /* Given */
        final Church cuhrch = Church.builder()
                .id(UUID.randomUUID().toString())
                .name("삼일 교회")
                .visitPurpose(VisitPurposeType.CHILD_CARE.getKey())
                .address(
                        Address.builder()
                                .basic("삼일교회 BASIC")
                                .detail("삼일교회 DETAIL")
                                .build()
                ).pastor(
                        Pastor.builder()
                                .name("삼일 교회 목사")
                                .phone("010-1234-5678")
                                .build()
                )
                .build();

        // When
        final GetChurchDto getChurchDto = ChurchMapper.INSTANCE.churchToGetChurchDto(cuhrch);

        System.out.println("getChurchDto = " + getChurchDto);
        // Then
        assertNotNull(getChurchDto);
        assertThat(getChurchDto.id()).isEqualTo(cuhrch.getId());
        assertThat(getChurchDto.name()).isEqualTo(cuhrch.getName());
        assertThat(getChurchDto.address()).isEqualTo(cuhrch.getAddress().getFullAddress());
        assertThat(getChurchDto.pastorName()).isEqualTo(cuhrch.getPastor().getName());
        assertThat(getChurchDto.pastorPhone()).isEqualTo(cuhrch.getPastor().getPhone());

    }

}
