package com.ospiridonovn.security;

import com.ospiridonovn.security.token.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ospiridonov on 22.08.2016.
 */
@Component
public class CorsFilter extends OncePerRequestFilter {

    @Autowired
    IAuthenticationService authenticationService;

    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpServletRequest.getMethod())) {
            // CORS "pre-flight" request
            httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, token");
            httpServletResponse.addHeader("Access-Control-Max-Age", "1");// 30 min
        }

        String token = httpServletRequest.getHeader("token");
        if (token == null) {
            token = httpServletRequest.getParameter("token");
        }
        if (token != null) {
            String key = "token_key";
            DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
            if (claims.get("token_expiration_date", Long.class) == null) {
                throw new AuthenticationServiceException("Invalid token");
            }
            Date expiredDate = new Date(claims.get("token_expiration_date", Long.class));
            if (expiredDate.before(new Date())) {
                throw new AuthenticationServiceException("Token expired date error");
            } else {
                String username = claims.get("username", String.class);
                String password = claims.get("password", String.class);
//                authenticationService.authenticate(username, password, Role.USER);
                List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
