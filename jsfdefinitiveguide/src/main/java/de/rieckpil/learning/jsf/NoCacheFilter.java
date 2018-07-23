package de.rieckpil.learning.jsf;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "facesServlet")
public class NoCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String resourcePath = request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER;

        if (!request.getRequestURI().startsWith(resourcePath)) {
            response.setHeader
                    ("Cache-Control", "no-store, must-revalidate");
        }

        chain.doFilter(request, response);
    }
}