package com.samill.missionary_backend.church.church.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.samill.missionary_backend.church.church.exception.NotFoundChurchException;
import com.samill.missionary_backend.church.dto.CreateChurchCommand;
import com.samill.missionary_backend.church.dto.GetChurchQueryResult;
import com.samill.missionary_backend.church.dto.GetChurchesQueryResult;
import com.samill.missionary_backend.church.dto.UpdateChurchCommand;
import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@WithMockUser(username = "dongwook.yeom")
@ExtendWith(MockitoExtension.class)
class ChurchServiceTests {

    @Autowired
    private ChurchService churchService;

    private List<String> churchIds = new ArrayList<>();


    @BeforeEach
    void setUp() throws CommonException {
        churchIds =
            new ArrayList<>(
                Collections.singletonList(
                    this.churchService.createChurch(
                        new CreateChurchCommand(
                            "name",
                            "pastorName",
                            "pastorPhone",
                            "addressBasic",
                            "addressDetail"
                        )
                    )
                )
            );

    }

    @AfterEach
    void tearDown() {
        churchIds.forEach(churchService::deleteChurch);
    }

    @Test
    void getChurches() {
        final GetChurchesQueryResult result = churchService.getChurches();

        assertThat(result.churches()).isNotEmpty();
        assertThat(result.hasNext()).isEqualTo(false);
    }

    @Test
    void getChurchThrowException() throws CommonException {
        var actual = assertThrows(NotFoundChurchException.class, () -> churchService.getChurch(UUID.randomUUID().toString()));
        System.out.println(actual.getMessage());
        assertThat(actual.getResponseCode()).isEqualTo(ResponseCode.NOT_FOUND_CHURCH_ERROR);
    }


    @Test
    void createChurch() throws CommonException {
        final String churchId = this.churchService.createChurch(
            new CreateChurchCommand(
                "name",
                "pastorName",
                "pastorPhone",
                "addressBasic",
                "addressDetail"
            )
        );

        final GetChurchQueryResult getChurchQueryResult = this.churchService.getChurch(churchId);

        System.out.println(getChurchQueryResult);

        assertThat(getChurchQueryResult.id()).isEqualTo(churchId);
    }

    @Test
    @Transactional
    void updateChurch() throws CommonException {

        final UpdateChurchCommand updateChurchCommand = new UpdateChurchCommand(
            "이름",
            "목사님 이름",
            "목사님 전화번호",
            "주소 기본",
            "주소 상세"
        );

        this.churchService.updateChurch(
            churchIds.getFirst(),
            updateChurchCommand
        );

        final GetChurchQueryResult getChurchQueryResult = this.churchService.getChurch(churchIds.getFirst());

        assertThat(getChurchQueryResult.name()).isEqualTo(updateChurchCommand.name());
        assertThat(getChurchQueryResult.pastorName()).isEqualTo(updateChurchCommand.pastorName());
        assertThat(getChurchQueryResult.pastorPhone()).isEqualTo(updateChurchCommand.pastorPhone());
        assertThat(getChurchQueryResult.address()).isEqualTo(updateChurchCommand.addressBasic() + " " + updateChurchCommand.addressDetail());


    }

    @Test
    @Transactional
    void deleteChurch() throws CommonException {
        this.churchService.deleteChurch(churchIds.getFirst());
        var actual = assertThrows(NotFoundChurchException.class, () -> churchService.getChurch(churchIds.getFirst()));
        assertThat(actual.getResponseCode()).isEqualTo(ResponseCode.NOT_FOUND_CHURCH_ERROR);
    }
}
