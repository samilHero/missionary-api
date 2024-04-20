package com.samill.missionary_backend.church.church.repository;


import com.samill.missionary_backend.church.church.dto.GetChurchesCursor;
import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.church.church.entity.VisitPurposeType;
import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaConfig.class, DateTimeProviderConfig.class})
class ChurchRepositoryTests {

    @Autowired
    private ChurchRepository churchRepository;

    private List<Church> churches = new ArrayList<>();

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
        churches.add(church);
        churchRepository.save(church);
    }

    @Test
    @DisplayName("find all with cursor")
    void findAllByCursor() {

        final List<Church> churches = churchRepository.findAllWithCursor(
                new GetChurchesCursor(
                        this.churches.get(0).getId(),
                        this.churches.get(0).getName()
                ),
                3
        );

        final List<Church> sortedChurches = this.churches.stream().filter(
                        church -> {

                            final int nameCompare = church.getName().compareTo(churches.get(0).getName());

                            if (nameCompare == 0) {
                                return church.getId().compareTo(churches.get(0).getId()) >= 0;
                            }

                            return nameCompare >= 0;
                        }
                )
                .sorted(Comparator.comparing(Church::getName).thenComparing(Church::getId))
                .toList();

        assertThat(churches).isNotEmpty();

        for (var i = 0; i < churches.size(); i++) {
            assertThat(churches.get(i).getId()).isEqualTo(sortedChurches.get(i).getId());
        }

    }


}
