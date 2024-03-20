package com.samill.missionaryBackend.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.samill.missionaryBackend.missionary.MissionaryDTO;
import com.samill.missionaryBackend.user.UserDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Disabled
public class AppRestControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void shouldAddNewEmployee() {
        MissionaryDTO emp = new MissionaryDTO(null, 1L, 1L, "Test", 30, "HR");
        emp = restTemplate.postForObject("/api/employees", emp, MissionaryDTO.class);
        assertNotNull(emp.id());
    }

    @Test
    @Order(1)
    void shouldAddNewDepartment() {
        UserDTO dep = new UserDTO(null, 1L, "Test");
        dep = restTemplate.postForObject("/api/departments", dep, UserDTO.class);
        assertNotNull(dep.id());
    }

    @Test
    @Order(2)
    void shouldFindDepartmentWithEmployees() {
        UserDTO dep = restTemplate.getForObject("/api/departments/{id}/with-employees", UserDTO.class, 1L);
        assertNotNull(dep);
        assertNotNull(dep.id());
    }
}
