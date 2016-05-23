package com.github.muellerc.techforum2016.jboss;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.Uri;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("books")
public class BooksResource {

    @Inject
    @Uri("direct:get-book")
    private ProducerTemplate getBook;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn}")
    public Book get(@PathParam("isbn") String isbn) {
        return getBook.requestBody((Object) isbn, Book.class);
    }
}