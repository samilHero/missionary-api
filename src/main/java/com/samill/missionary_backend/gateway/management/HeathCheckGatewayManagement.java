package com.samill.missionary_backend.gateway.management;


import static com.samill.missionary_backend.gateway.endPoint.HealthCheckGatewayManagementEndPoint.HEALTH_BASE_URL;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HeathCheckGatewayManagement {

    @GetMapping(HEALTH_BASE_URL)
    public void healthCheck() {
        log.info("health-check");
    }
}
