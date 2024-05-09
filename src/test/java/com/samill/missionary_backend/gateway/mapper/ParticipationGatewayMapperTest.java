package com.samill.missionary_backend.gateway.mapper;

import com.samill.missionary_backend.gateway.dto.Participation.GetParticipationResult;
import com.samill.missionary_backend.gateway.mapper.ParticipationGatewayMapper;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.participation.dto.GetParticipationsQuery;
import com.samill.missionary_backend.participation.dto.GetParticipationQueryResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParticipationGatewayMapperTest {
    @Autowired
    private ParticipationExternalService participationExternalService;

    @Test
    void 매핑테스트() {
        //given
        GetParticipationsQuery query = GetParticipationsQuery.builder()
                .missionaryId("71d8cee6-e2bc-472a-ab1f-c61c70dc0e51")
                .pageSize(10)
                .build();

        //when
        List<GetParticipationQueryResult> participationQueryResults = participationExternalService.getParticipations(query);
        List<GetParticipationResult> result = ParticipationGatewayMapper.INSTANCE.getParticipationQueryResultsToGetParticipationResults(participationQueryResults);

        //then
        System.out.println(result.get(0).getName());
        assertEquals("홍길동", result.get(0).getName());
    }
}