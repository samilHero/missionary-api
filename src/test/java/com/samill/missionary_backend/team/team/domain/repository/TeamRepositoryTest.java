package com.samill.missionary_backend.team.team.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("find all missionary by id")
    void findAllByMissionaryId() {
        System.out.println(teamRepository.findAllByMissionaryId("1"));
//        assertNotNull(teamRepository.findAllByMissionaryId("1"));
    }
}