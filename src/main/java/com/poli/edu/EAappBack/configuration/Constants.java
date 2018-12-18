package com.poli.edu.EAappBack.configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class Constants {

    // Spring Security
    public static final String LOGIN_URL = "/login";
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    // JWT
    public static final Key SUPER_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final long TOKEN_EXPIRATION_TIME = 3600000; // 1 hora
}
