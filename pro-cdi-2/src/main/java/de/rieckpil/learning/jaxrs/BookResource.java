package de.rieckpil.learning.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("books")
public class BookResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getBooks(@Context HttpHeaders httpHeaders) {
        return Response.ok(httpHeaders
                .getRequestHeaders()
                .keySet()
                .stream()
                .map(header -> header + ": " + httpHeaders.getRequestHeaders().getFirst(header)).collect(Collectors.joining("\n"))).build();

    }
}
