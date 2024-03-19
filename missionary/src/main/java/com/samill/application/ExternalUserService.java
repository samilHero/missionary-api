package com.samill.application;

public interface ExternalUserService {
    boolean checkUserRoleType(RoleType roleType, String userId);
}
