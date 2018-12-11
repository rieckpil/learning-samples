package sample;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TimeOfDayFilter", urlPatterns = {"/resources/sample"})
public class SampleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getLocalPort());
        System.out.println(request.getLocalAddr());
        System.out.println(request.getProtocol());
        System.out.println(request.getServerName());
        System.out.println(request.getServletContext().getContextPath());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
