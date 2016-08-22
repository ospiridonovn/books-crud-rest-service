package com.ospiridonovn.rest;

import com.ospiridonovn.domain.Book;
import com.ospiridonovn.security.CustomAuthenticationProvider;
import com.ospiridonovn.security.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/login")
public class LoginService {

    @Autowired
    IAuthenticationService authenticationService;

    @GET
    @Path("/{username}-{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("username") String username, @PathParam("password") String password) {
        Boolean result = authenticationService.authenticate(username, password);

        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/logout")
    public Response logout() {


        return Response.status(200).build();
    }
}
