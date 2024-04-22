package com.samill.missionary_backend.church.church.service;

import com.samill.missionary_backend.church.church.dto.GetChurchesRequest;
import com.samill.missionary_backend.church.church.dto.GetChurchesResult;
import com.samill.missionary_backend.church.church.entity.Church;
import com.samill.missionary_backend.church.church.repository.ChurchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChurchServiceTests {
    @Mock
    private ChurchRepository churchRepository;

    @InjectMocks
    private ChurchService churchService;

    @Test
    void getChurches() {
        // given
        final GetChurchesRequest getChurchesRequest = new GetChurchesRequest(null, 10);

        final List<Church> churches = List.of(
                mock(Church.class),
                mock(Church.class)
        );

        final Slice<Church> churcheSlice = new SliceImpl<>(
                churches,
                PageRequest.of(0, getChurchesRequest.pageSize()),
                false
        );


        when(churches.get(0).getId()).thenReturn("1");
        when(churches.get(0).getName()).thenReturn("바 교회");
        when(churches.get(1).getId()).thenReturn("2");
        when(churches.get(1).getName()).thenReturn("마 교회");
        when(churchRepository.findAllWithCursor(getChurchesRequest.cursor(), PageRequest.of(0, getChurchesRequest.pageSize()))).thenReturn(churcheSlice);

        // when
        final GetChurchesResult getChurchesResult = churchService.getChurches(getChurchesRequest);

        // then


        assertNotNull(getChurchesResult);
        assertThat(getChurchesResult.hasNext()).isEqualTo(false);
        assertThat(getChurchesResult.churches().size()).isEqualTo(2);
        assertThat(getChurchesResult.churches().get(0).id()).isEqualTo("1");
        assertThat(getChurchesResult.churches().get(0).name()).isEqualTo("바 교회");
        assertThat(getChurchesResult.churches().get(1).id()).isEqualTo("2");
        assertThat(getChurchesResult.churches().get(1).name()).isEqualTo("마 교회");


    }


}
