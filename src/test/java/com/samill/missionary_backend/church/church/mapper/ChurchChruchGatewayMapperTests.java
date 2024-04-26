package com.samill.missionary_backend.church.church.mapper;

import com.samill.missionary_backend.church.church.dto.CreateChurchCommand;
import com.samill.missionary_backend.church.church.dto.GetChurchQueryResult;
import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ChurchChruchGatewayMapperTests {

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
        final GetChurchQueryResult getChurchQueryResult = ChurchMapper.INSTANCE.churchToGetChurchResult(cuhrch);

        System.out.println("getChurchDto = " + getChurchQueryResult);
        // Then
        assertNotNull(getChurchQueryResult);
        assertThat(getChurchQueryResult.id()).isEqualTo(cuhrch.getId());
        assertThat(getChurchQueryResult.name()).isEqualTo(cuhrch.getName());
        assertThat(getChurchQueryResult.address()).isEqualTo(cuhrch.getAddress().getFullAddress());
        assertThat(getChurchQueryResult.pastorName()).isEqualTo(cuhrch.getPastor().getName());
        assertThat(getChurchQueryResult.pastorPhone()).isEqualTo(cuhrch.getPastor().getPhone());

    }


    @Test
    @DisplayName("CreateChurchDto to Church Test")
    void createChurchDtoToChurch() {
        /* Given */
        final CreateChurchCommand createChurchCommand = new CreateChurchCommand(
                "삼일 교회",
                "삼일 교회 목사",
                "010-1234-5678",
                "삼일교회 BASIC",
                "삼일교회 DETAIL"
        );

        /* When */
        final Church church = ChurchMapper.INSTANCE.createChurchRequestToChurch(createChurchCommand);

        /* Then */
        assertNotNull(church);
        assertThat(church.getName()).isEqualTo(createChurchCommand.name());
        assertThat(church.getPastor().getName()).isEqualTo(createChurchCommand.pastorName());
        assertThat(church.getPastor().getPhone()).isEqualTo(createChurchCommand.pastorPhone());
        assertThat(church.getAddress().getBasic()).isEqualTo(createChurchCommand.addressBasic());
        assertThat(church.getAddress().getDetail()).isEqualTo(createChurchCommand.addressDetail());

    }


}
