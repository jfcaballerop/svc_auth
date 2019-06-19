package com.mrknight.svcauth.security.jwt;

public class SecurityConstants {

    // Spring Security

    public static final String LOGIN_URL = "/login";
    public static final String SIGNIN_URL = "/signin";
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String TOKEN_PREFIX = "token";

    // JWT

    public static final String ISSUER_INFO = "https://www.mrknight.com/";
    public static final String SUPER_SECRET_KEY = "Mrknight_n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

    /** Roles */
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_TEACHER = "TEACHER";
    public static final String ROLE_STUDENT = "STUDENT";
}