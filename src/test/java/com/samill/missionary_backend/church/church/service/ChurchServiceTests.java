package com.samill.missionary_backend.church.church.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.samill.missionary_backend.church.church.dto.CreateChurchRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchResult;
import com.samill.missionary_backend.church.church.dto.UpdateChurchRequest;
import com.samill.missionary_backend.church.church.exception.NotFoundChurchException;
import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
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

    //    @Mock

    //    @InjectMocks
    @Autowired
    private ChurchService churchService;

    private List<String> churchIds = new ArrayList<>();


    @BeforeEach
    void setUp() throws CommonException {
        churchIds =
            new ArrayList<>(
                Collections.singletonList(
                    this.churchService.createChurch(
                        "memberId",
                        new CreateChurchRequest(
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


    @Test
    void getChurches() {
        // given
//        final GetChurchesRequest getChurchesRequest = new GetChurchesRequest(null, 10);
//
//        final List<Church> churches = List.of(
//            mock(Church.class),
//            mock(Church.class)
//        );
//
//        final Slice<Church> churcheSlice = new SliceImpl<>(
//            churches,
//            PageRequest.of(0, getChurchesRequest.pageSize()),
//            false
//        );
//
//        when(churches.get(0).getId()).thenReturn("1");
//        when(churches.get(0).getName()).thenReturn("바 교회");
//        when(churches.get(1).getId()).thenReturn("2");
//        when(churches.get(1).getName()).thenReturn("마 교회");
//        when(churchRepository.findAllWithCursor(getChurchesRequest.cursor(), PageRequest.of(0, getChurchesRequest.pageSize()))).thenReturn(
//            churcheSlice);
//
//        // when
//        final GetChurchesResult getChurchesResult = churchService.getChurches(getChurchesRequest);
//
//        // then
//
//        assertNotNull(getChurchesResult);
//        assertThat(getChurchesResult.hasNext()).isEqualTo(false);
//        assertThat(getChurchesResult.churches().size()).isEqualTo(2);
//        assertThat(getChurchesResult.churches().get(0).id()).isEqualTo("1");
//        assertThat(getChurchesResult.churches().get(0).name()).isEqualTo("바 교회");
//        assertThat(getChurchesResult.churches().get(1).id()).isEqualTo("2");
//        assertThat(getChurchesResult.churches().get(1).name()).isEqualTo("마 교회");
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
            "memberId",
            new CreateChurchRequest(
                "name",
                "pastorName",
                "pastorPhone",
                "addressBasic",
                "addressDetail"
            )
        );

        final GetChurchResult getChurchResult = this.churchService.getChurch(churchId);

        System.out.println(getChurchResult);

        assertThat(getChurchResult.id()).isEqualTo(churchId);
    }

    @Test
    @Transactional
    void updateChurch() throws CommonException {

        final UpdateChurchRequest updateChurchRequest = new UpdateChurchRequest(
            "이름",
            "목사님 이름",
            "목사님 전화번호",
            "주소 기본",
            "주소 상세"
        );

        this.churchService.updateChurch(
            churchIds.getFirst(),
            "memberId",
            updateChurchRequest
        );

        final GetChurchResult getChurchResult = this.churchService.getChurch(churchIds.getFirst());

        assertThat(getChurchResult.name()).isEqualTo(updateChurchRequest.name());
        assertThat(getChurchResult.pastorName()).isEqualTo(updateChurchRequest.pastorName());
        assertThat(getChurchResult.pastorPhone()).isEqualTo(updateChurchRequest.pastorPhone());
        assertThat(getChurchResult.address()).isEqualTo(updateChurchRequest.addressBasic() + " " + updateChurchRequest.addressDetail());


    }

    @Test
    @Transactional
    void deleteChurch() throws CommonException {
        this.churchService.deleteChurch(churchIds.getFirst(), "memberId");
        var actual = assertThrows(NotFoundChurchException.class, () -> churchService.getChurch(churchIds.getFirst()));
        assertThat(actual.getResponseCode()).isEqualTo(ResponseCode.NOT_FOUND_CHURCH_ERROR);
    }
}
