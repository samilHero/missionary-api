package com.samill.missionary_backend.gateway.endpoint;

public class AdminGatewayManagementEndpoint {

    public static final String BASE_URL = "/api/admin";

    public static final String GET_CHURCH = BASE_URL + "/churches/{churchId}";

    public static final String GET_CHURCHES = BASE_URL + "/churches";

    public static final String CREATE_CHURCH = BASE_URL + "/churches";

    public static final String DELETE_CHURCH = BASE_URL + "/churches/{churchId}";

    public static final String UPDATE_CHURCH = BASE_URL + "/churches/{churchId}";


}
