package com.ospiridonovn.security;

import com.ospiridonovn.security.token.IGetTokenService;
import com.ospiridonovn.security.token.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Autowired
    IGetTokenService tokenService;

    public String authenticate(String username, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationProvider.authenticate(token);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return tokenService.getToken(username, password, Role.USER);
        } else {
            return null;
        }
    }

    public boolean authenticate(String username, String password, Role role) {
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
}
