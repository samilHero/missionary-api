package com.samill.missionary_backend.gateway.endPoint;

public class UserGatewayManagementEndPoint {

    public static final String BASE_URL = "/api/user";
    public static final String PUBLIC_URL = "/api/public/user";
    public static final String USER_LOGIN_URI = BASE_URL + "/login";
    public static final String GET_USER_URI = BASE_URL + "/user";
    public static final String CREATE_USER_URI = BASE_URL + "/user";
    public static final String GET_IS_EXISTED_USER_ID_URI = PUBLIC_URL + "/{loginId}";
    public static final String GET_MISSIONARIES = BASE_URL + "/missionaries";
    public static final String GET_MISSIONARY = BASE_URL + "/missionaries/{missionaryId}";

    public static final String CREATE_PARTICIPATION = BASE_URL + "/participation";

    public static final String UPDATE_PARTICIPATION = BASE_URL + "/participation/{participationId}";

    public static final String DELETE_PARTICIPATION = BASE_URL + "/participation/{participationId}";
}
