package com.poli.edu.EAappBack.configuration;

import static com.poli.edu.EAappBack.configuration.Constants.LOGIN_URL;
import static com.poli.edu.EAappBack.configuration.Constants.HEADER_AUTHORIZACION_KEY;
import static com.poli.edu.EAappBack.configuration.Constants.TOKEN_BEARER_PREFIX;
import static com.poli.edu.EAappBack.configuration.Constants.TOKEN_EXPIRATION_TIME;
import static com.poli.edu.EAappBack.configuration.Constants.SUPER_SECRET_KEY;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poli.edu.EAappBack.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_URL));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario credenciales = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credenciales.getEmail(), credenciales.getClave()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws java.io.IOException, ServletException {
        Claims cliams = Jwts.claims();
        cliams.put("authorities", authResult.getAuthorities());

        String token = Jwts.builder().setIssuedAt(new Date())
                .setClaims(cliams)
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SUPER_SECRET_KEY)
                .compact();

        response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("user", (User) authResult.getPrincipal());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();
        body.put("message", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }

}
