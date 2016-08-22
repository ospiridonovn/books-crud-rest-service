package com.ospiridonovn.rest;

import com.ospiridonovn.domain.Book;
import com.ospiridonovn.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/book")
public class BookRestService {

    @Autowired
    IBookService bookService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Book get(@PathParam("id") String id) {
        return bookService.get(Long.parseLong(id));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Response add(Book book) {
        bookService.add(book);
        return Response.status(200).build();
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Book update(Book book) {
        bookService.update(book);
        return book;
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Book delete(@PathParam("id") String id) {
        bookService.delete(bookService.get(Long.parseLong(id)));
        return bookService.get(Long.parseLong(id));
    }


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
//        return (List<Book>) bookService.list();
        return Response.ok() //200
                .entity(bookService.list())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();

    }
}
