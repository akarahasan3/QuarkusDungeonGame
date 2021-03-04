package com.library;

import com.library.services.LibraryServices;
import com.library.services.dto.BookDto;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/library")
public class LibraryResource {
    @Inject
    LibraryServices libraryServices;

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
        List<BookDto> bookDtoList = libraryServices.getAllBooks();
        return Response.ok(bookDtoList).build();
    }

}
