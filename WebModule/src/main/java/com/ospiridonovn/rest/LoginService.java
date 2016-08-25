package com.ospiridonovn.rest;

import com.ospiridonovn.domain.Book;
import com.ospiridonovn.security.IAuthenticationService;
import com.ospiridonovn.security.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Component
@Path("/login")
public class LoginService {

    @Autowired
    IAuthenticationService authenticationService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest req, UserData userData) {
//        String username = uriInfo.getQueryParameters().getFirst("username");
//        String password = uriInfo.getQueryParameters().getFirst("password");
//        String username = uriInfo.getQueryParameters().getFirst("username");
//        String password = uriInfo.getQueryParameters().getFirst("password");
        String token = authenticationService.authenticate(userData.getUsername(), userData.getPassword(), req);
//        Boolean result = authenticationService.authenticate("user", "password");
        HttpSession session = req.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return Response.status(200).entity(token)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }

    @GET
    @Path("/logout")
    public Response logout() {


        return Response.status(200).build();
    }
}
