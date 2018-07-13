package de.rieckpil.learning.user.boundary;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import java.io.IOException;

@WebFilter(filterName = "PushFilter", urlPatterns = {"/*"})
public class PushFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;

        PushBuilder builder =  httpReq.newPushBuilder();

        if (builder != null) {
            System.out.println("Filtered");
            builder
                    .path("resources/javaee-logo.png")
                    .path("resources/style.css")
                    .path("resources/functions.js")
                    .push();
            System.out.println("Resources pushed");
        }

        chain.doFilter(request, response);

    }
}