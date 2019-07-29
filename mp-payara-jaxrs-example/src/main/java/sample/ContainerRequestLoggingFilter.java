package sample;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ContainerRequestLoggingFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("=======================");
        requestContext.getHeaders().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("=======================");
    }
}
