package com.samill.missionary_backend.church.church.repository;


import com.samill.missionary_backend.church.church.dto.GetChurchesQueryCursor;
import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaConfig.class, DateTimeProviderConfig.class})
class MissionaryChurchRepositoryTests {

    private final List<Church> churches = new ArrayList<>();
    @Autowired
    private ChurchRepository churchRepository;

    @BeforeEach
    void setUp() {
        _generateChurch("바 교회");
        _generateChurch("마 교회");
        _generateChurch("가 교회");
        _generateChurch("가 교회");
        _generateChurch("가 교회345");
        _generateChurch("카 교회");
        _generateChurch("차 교회");
        _generateChurch("다 교회");
        _generateChurch("아 교회");
        _generateChurch("자 교회");
        _generateChurch("사 교회");
        _generateChurch("나 교회");
        _generateChurch("라 교회");
    }

    void _generateChurch(String name) {
        Church church = Church.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
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
        churches.add(church);
        churchRepository.save(church);
    }

    @Test
    @DisplayName("find all with cursor")
    void findAllByCursor() {

        final Slice<Church> churcheSlice = churchRepository.findAllWithCursor(
                new GetChurchesQueryCursor(
                        this.churches.get(0).getId(),
                        this.churches.get(0).getName()
                ),
                PageRequest.of(0, 3)
        );

        final List<Church> sortedChurches = this.churches.stream().filter(
                        church -> {

                            final int nameCompare = church.getName().compareTo(churcheSlice.getContent().get(0).getName());

                            if (nameCompare == 0) {
                                return church.getId().compareTo(churcheSlice.getContent().get(0).getId()) >= 0;
                            }

                            return nameCompare >= 0;
                        }
                )
                .sorted(Comparator.comparing(Church::getName).thenComparing(Church::getId))
                .toList();

        assertThat(churcheSlice).isNotEmpty();

        for (var i = 0; i < churcheSlice.getSize(); i++) {
            assertThat(churcheSlice.getContent().get(i).getId()).isEqualTo(sortedChurches.get(i).getId());
        }

    }


}
