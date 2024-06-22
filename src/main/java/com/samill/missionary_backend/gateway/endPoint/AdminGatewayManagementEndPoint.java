package com.samill.missionary_backend.gateway.endPoint;

public class AdminGatewayManagementEndPoint {

    public static final String BASE_URL = "/api/admin";

    public static final String GET_CHURCH = BASE_URL + "/churches/{churchId}";

    public static final String GET_CHURCHES = BASE_URL + "/churches";

    public static final String CREATE_CHURCH = BASE_URL + "/churches";

    public static final String DELETE_CHURCH = BASE_URL + "/churches/{churchId}";

    public static final String UPDATE_CHURCH = BASE_URL + "/churches/{churchId}";

    public static final String ADMIN_LOGIN_URI = BASE_URL + "/login";

    public static final String CREATE_ADMIN_URI = BASE_URL + "/admin";

    public static final String GET_MISSIONARY = BASE_URL + "/missionaries/{missionaryId}";

    public static final String GET_MISSIONARIES = BASE_URL + "/missionaries";

    public static final String CREATE_MISSIONARY = BASE_URL + "/missionaries";

    public static final String UPDATE_MISSIONARY = BASE_URL + "/missionaries/{missionaryId}";

    public static final String DELETE_MISSIONARY = BASE_URL + "/missionaries/{missionaryId}";

    public static final String GET_PARTICIPATIONS = BASE_URL + "/participations/{missionaryId}";

    public static final String GET_PARTICIPATION = BASE_URL + "/participation/{participationId}";

    public static final String UPDATE_PARTICIPATION_APPROVE = BASE_URL + "/participation/approve";

    public static final String GET_DOWNLOAD_PARTICIPATION_LIST = BASE_URL + "/participation/download/{missionaryId}";

    public static final String UPDATE_TEAM_MEMBER = BASE_URL + "/team/{teamId}/members";
}
