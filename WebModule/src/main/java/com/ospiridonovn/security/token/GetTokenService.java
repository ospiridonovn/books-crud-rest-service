package com.ospiridonovn.security.token;

import com.ospiridonovn.security.Role;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class GetTokenService implements IGetTokenService {

    public String getToken(String username, String password, Role role) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("username", username);
        data.put("password", password);
        data.put("role", role);
        data.put("token_create_date", new Date().getTime());
        Calendar month = Calendar.getInstance();
        month.add(Calendar.YEAR, 100);
        data.put("token_expiration_date", month);
        JwtBuilder builder = Jwts.builder();
        builder.setExpiration(month.getTime());
        builder.setClaims(data);
        String key = "token_key";
        return builder.signWith(SignatureAlgorithm.HS512, key).compact();
    }
}
