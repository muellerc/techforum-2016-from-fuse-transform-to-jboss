package com.github.muellerc.techforum2016.fuse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BooksResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn}")
    public Book get(@PathParam("isbn") String isbn) {
        return null;
    }
}