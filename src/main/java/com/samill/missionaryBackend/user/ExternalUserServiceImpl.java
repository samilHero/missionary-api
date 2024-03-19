package com.samill.missionaryBackend.user;

import com.samill.application.ExternalUserService;
import com.samill.application.RoleType;

public class ExternalUserServiceImpl implements ExternalUserService {
    public boolean checkUserRoleType(RoleType roleType, String userId) {
        return false;
    }
}
