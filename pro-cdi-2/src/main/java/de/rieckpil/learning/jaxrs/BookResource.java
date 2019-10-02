package de.rieckpil.learning.jaxrs;

import de.rieckpil.learning.MyQualifier;
import de.rieckpil.learning.MyService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("books")
@RequestScoped
public class BookResource {

    @Inject
    @MyQualifier
    private MyService myService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getBooks(@Context HttpHeaders httpHeaders) {

        System.out.println("myService.toString() = " + myService.toString());
        System.out.println("this.toString() = " + this.toString());

        return Response.ok(httpHeaders
                .getRequestHeaders()
                .keySet()
                .stream()
                .map(header -> header + ": " + httpHeaders.getRequestHeaders().getFirst(header)).collect(Collectors.joining("\n"))).build();

    }
}
