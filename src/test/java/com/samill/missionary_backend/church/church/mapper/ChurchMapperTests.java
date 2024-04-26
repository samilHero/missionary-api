package com.samill.missionary_backend.church.church.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.samill.missionary_backend.church.church.dto.CreateChurchRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchResult;
import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChurchMapperTests {

    @Test
    @DisplayName("Church to GetChurchDto Test")
    void churchToGetChurchDtoTest() {
        /* Given */
        final Church cuhrch = Church.builder()
            .id(UUID.randomUUID().toString())
            .name("삼일 교회")
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
        final GetChurchResult getChurchResult = ChurchMapper.INSTANCE.churchToGetChurchResult(cuhrch);

        System.out.println("getChurchDto = " + getChurchResult);
        // Then
        assertNotNull(getChurchResult);
        assertThat(getChurchResult.id()).isEqualTo(cuhrch.getId());
        assertThat(getChurchResult.name()).isEqualTo(cuhrch.getName());
        assertThat(getChurchResult.address()).isEqualTo(cuhrch.getAddress().getFullAddress());
        assertThat(getChurchResult.pastorName()).isEqualTo(cuhrch.getPastor().getName());
        assertThat(getChurchResult.pastorPhone()).isEqualTo(cuhrch.getPastor().getPhone());

    }


    @Test
    @DisplayName("CreateChurchDto to Church Test")
    void createChurchDtoToChurch() {
        /* Given */
        final CreateChurchRequest createChurchRequest = new CreateChurchRequest(
            "삼일 교회",
            "삼일 교회 목사",
            "010-1234-5678",
            "삼일교회 BASIC",
            "삼일교회 DETAIL"
        );

        /* When */
        final Church church = ChurchMapper.INSTANCE.createChurchRequestToChurch(createChurchRequest);

        /* Then */
        assertNotNull(church);
        assertThat(church.getName()).isEqualTo(createChurchRequest.name());
        assertThat(church.getPastor().getName()).isEqualTo(createChurchRequest.pastorName());
        assertThat(church.getPastor().getPhone()).isEqualTo(createChurchRequest.pastorPhone());
        assertThat(church.getAddress().getBasic()).isEqualTo(createChurchRequest.addressBasic());
        assertThat(church.getAddress().getDetail()).isEqualTo(createChurchRequest.addressDetail());

    }


}
