package com.ospiridonovn.security;

/**
 * Created by ospiridonov on 22.08.2016.
 */
public interface IAuthenticationService {
    public boolean authenticate(String username, String password);
}
