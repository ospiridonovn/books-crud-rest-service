package com.ospiridonovn.security.authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ospiridonov on 22.08.2016.
 */
public interface IAuthenticationService {
    public String authenticate(String username, String password, HttpServletRequest request);
}
