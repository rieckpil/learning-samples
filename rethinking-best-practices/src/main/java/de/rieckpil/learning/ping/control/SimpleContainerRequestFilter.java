package de.rieckpil.learning.ping.control;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@RateLimited
public class SimpleContainerRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        System.out.println(requestContext.getCookies());
        System.out.println(requestContext.getDate());
        System.out.println(requestContext.getMethod());
        requestContext.getHeaders().forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println(requestContext.getCookies());
        System.out.println(requestContext.getLanguage());
        System.out.println(requestContext.getSecurityContext().getUserPrincipal());
        System.out.println(requestContext.getUriInfo());

        requestContext.abortWith(Response.status(Response.Status.TOO_MANY_REQUESTS).build());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("X-REMAINING-API-CALLS", 1);
        JsonObject result = Json.createObjectBuilder().add("msg", "too many requests").build();

        responseContext.setEntity(result, null, MediaType.APPLICATION_JSON_TYPE);
    }
}
