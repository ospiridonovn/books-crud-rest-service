package com.ospiridonovn.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ospiridonov on 22.08.2016.
 */
@Component
public class CorsFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpServletRequest.getMethod())) {
            // CORS "pre-flight" request
            httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
            httpServletResponse.addHeader("Access-Control-Max-Age", "1");// 30 min
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
