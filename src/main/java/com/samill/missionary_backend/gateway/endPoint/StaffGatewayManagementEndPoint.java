package com.samill.missionary_backend.gateway.endPoint;

public class StaffGatewayManagementEndPoint {


    public static final String BASE_URL = "/api/staff";

    public static final String GET_CHURCH = BASE_URL + "/churches/{churchId}";

    public static final String GET_CHURCHES = BASE_URL + "/churches";

    public static final String GET_PARTICIPATIONS = BASE_URL + "/participations";

    public static final String GET_PARTICIPATION = BASE_URL + "/participation/{participationId}";
}
