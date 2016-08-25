package com.ospiridonovn.security.authentication;

import com.ospiridonovn.security.token.IGetTokenService;
import com.ospiridonovn.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService implements IAuthenticationService {

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
}
