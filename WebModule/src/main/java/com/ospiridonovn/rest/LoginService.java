package com.ospiridonovn.rest;

import com.ospiridonovn.security.authentication.IAuthenticationService;
import com.ospiridonovn.security.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/login")
public class LoginService {

    @Autowired
    IAuthenticationService authenticationService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest req, UserData userData) {
        String token = authenticationService.authenticate(userData.getUsername(), userData.getPassword(), req);
        return Response.status(200).entity(token)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
}
