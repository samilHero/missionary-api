package com.samill.missionary_backend.gateway.endPoint;

public class StaffGatewayManagementEndPoint {


    public static final String BASE_URL = "/api/staff";

    public static final String GET_CHURCH = BASE_URL + "/churches/{churchId}";

    public static final String GET_CHURCHES = BASE_URL + "/churches";

    public static final String GET_PARTICIPATIONS = BASE_URL + "/participations";

    public static final String GET_PARTICIPATION = BASE_URL + "/participation/{participationId}";

    public static final String CREATE_TEAM = BASE_URL + "/team";

    public static final String GET_TEAMS = BASE_URL + "/teams/{missionaryId}";

    public static final String GET_TEAM = BASE_URL + "/team/{teamId}";

    public static final String UPDATE_TEAM = BASE_URL + "/team/{teamId}";

    public static final String UPDATE_TEAM_MEMBER = BASE_URL + "/team/{teamId}/members";

    public static final String DELETE_TEAM = BASE_URL + "/team/{teamId}";
}
