package com.samill.missionary_backend.user;

import com.samill.missionary_backend.common.AbstractSpringBootTestsBase;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;


class UserServiceTests extends AbstractSpringBootTestsBase {

    @Test
    @DisplayName("get users by ids")
    @Transactional
    void getUsersByIds() {
        var userIds = List.of(
            "ee6fc2ad-5f3a-41a0-bce1-2f049b6742aa",
            "8ff1051f-085c-42ee-bacc-06656c9bf8db"
        );
        var users = memberExternalService.getUsersByIds(
            userIds
        );

        Assertions.assertFalse(users.stream().filter(user -> !userIds.contains(user.id())).toList().size() > 0);
    }

}
