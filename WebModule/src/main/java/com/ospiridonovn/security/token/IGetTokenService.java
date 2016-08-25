package com.ospiridonovn.security.token;

import com.ospiridonovn.security.Role;

/**
 * Created by ospiridonov on 24.08.2016.
 */
public interface IGetTokenService {
    public String getToken(String username, String password, Role role);

}
