package com.samill.missionary_backend.missionary.board;

import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.configs.DateTimeProviderConfig;
import com.samill.missionary_backend.configs.JpaConfig;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.board.repository.MissionaryBoardRepository;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.repository.MissionaryRepository;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({JpaConfig.class, DateTimeProviderConfig.class})
class MissionaryBoardTests {

    @Autowired
    private MissionaryBoardRepository missionaryBoardRepository;

    @Autowired
    private MissionaryRepository missionaryRepository;


    @Test
    @Rollback(value = false)
    void test() {

        final Missionary missionary = missionaryRepository.save(
            Missionary.builder()
                .name("Samuel")
                .period(
                    Period.builder()
                        .startDate(OffsetDateTime.now())
                        .endDate(OffsetDateTime.now().plusDays(10))
                        .build()
                )
                .pastor(
                    Pastor.builder()
                        .name("Pastor")
                        .phone("1234567890")
                        .build()
                )
                .build()
        );

        missionaryBoardRepository.save(
            MissionaryBoard.builder()
                .title("title")
                .content("content")
                .missionary(Missionary.builder().id(missionary.getId()).build())
                .build()
        );


    }

}
