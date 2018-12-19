package com.poli.edu.EAappBack.configuration;

import static com.poli.edu.EAappBack.configuration.Constants.HEADER_AUTHORIZACION_KEY;
import static com.poli.edu.EAappBack.configuration.Constants.TOKEN_BEARER_PREFIX;
import static com.poli.edu.EAappBack.configuration.Constants.SUPER_SECRET_KEY;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_AUTHORIZACION_KEY);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZACION_KEY);
        if (token != null) {
            // Se procesa el token y se recupera el usuario.
            Claims login = Jwts.parser()
                    .setSigningKey(SUPER_SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
                    .getBody();

            ArrayList<LinkedHashMap> autorities = (ArrayList<LinkedHashMap>) login.get("authorities");
            List<GrantedAuthority> autority = new ArrayList<>();
            autorities.stream().forEach((a) -> autority.add(new SimpleGrantedAuthority(a.get("authority").toString())));

            String user = login.getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, autority);
            }
            return null;
        }
        return null;
    }

}
