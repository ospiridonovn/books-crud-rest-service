package com.ospiridonovn.security;

import com.ospiridonovn.security.token.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ospiridonov on 22.08.2016.
 */
public interface IAuthenticationService {
    public String authenticate(String username, String password, HttpServletRequest request);
    public boolean authenticate(String username, String password, Role role);
}
