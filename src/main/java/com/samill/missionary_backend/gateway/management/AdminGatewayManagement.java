package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.gateway.ChurchGatewayMapper;
import com.samill.missionary_backend.gateway.dto.GetChurchResult;
import com.samill.missionary_backend.gateway.dto.GetChurchesResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AdminGatewayManagement.BASE_URL)
public class AdminGatewayManagement {

    static final String BASE_URL = "/api/admin";

    private final ChurchExternalService churchExternalService;

    @GetMapping("/churches/{id}")
    public GetChurchResult getChurch(@PathVariable String id) throws CommonException {
        return ChurchGatewayMapper.INSTANCE.getChruchQueryResultToGetChurchResult(churchExternalService.getChurch(id));
    }


    @GetMapping("/churches")
    public GetChurchesResult getChurches(
//            @RequestParam(value="")
    ) throws CommonException {
        churchExternalService.getChurches();
    }
}
