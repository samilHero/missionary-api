package com.samill.missionary_backend.church.repository;


import com.samill.missionary_backend.RepositoryTestConfig;
import com.samill.missionary_backend.church.entity.Church;
import com.samill.missionary_backend.church.entity.VisitPurposeType;
import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.Pastor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(RepositoryTestConfig.class)
class ChurchRepositoryTests {

    @Autowired
    private ChurchRepository churchRepository;


    @BeforeEach
    void setUp() {
        _generateChurch("바 교회");
        _generateChurch("마 교회");
        _generateChurch("가 교회");
        _generateChurch("가 교회");
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

        churchRepository.save(
                church
        );

    }

    @Test
    @DisplayName("find all with cursor")
    void findAllByCursor() {

        List<Church> churches = churchRepository.findAllWithCursorWithPageSize(null, 30);

        churches.stream()
                .map((church) -> "id" + church.getId() + " name: " + church.getName() + " createdAt: " + church.getCreatedAt())
                .forEach(System.out::println);
//        assertThat(churches).isNotEmpty();
//        assertThat(churches.get(0).getName()).isEqualTo("가 교회");

//        Optional<Church> cursorChurch = churchRepository.findById(churches.getLast().getId());
//
//        List<Church> nextChurches = churchRepository.findAllWithCursorWithPageSize(cursorChurch.orElseThrow().getName(), 100);
//
//        nextChurches.stream().map(Church::getName).forEach(System.out::println);
//
//        assertThat(nextChurches).isNotEmpty();
//        assertThat(nextChurches.getLast().getName()).isEqualTo("카 교회");
    }


}
