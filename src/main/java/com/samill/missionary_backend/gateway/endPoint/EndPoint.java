package com.samill.missionary_backend.gateway.endPoint;

public class EndPoint {

    public static final String API_ROOT_URI = "/api";
    public static final String MEMBER_ROOT_URI = API_ROOT_URI + "/member";

    public static final String USER_URI = MEMBER_ROOT_URI + "/user";
    public static final String USER_LOGIN_URI = MEMBER_ROOT_URI + "/user/token";
    public static final String AUTH_ROOT_URI = API_ROOT_URI + "/auth";
    public static final String TOKEN_URI = AUTH_ROOT_URI + "/token";


}
