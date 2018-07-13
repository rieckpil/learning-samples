package de.rieckpil.learning.user.boundary;

import de.rieckpil.learning.user.entity.JpaUser;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private JpaUser jpaUser;

    @PostConstruct
    public void instantiateUser(){
        jpaUser = new JpaUser("Philip Riecks", "hello@mail.com");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() +
                " has started");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() +
                " has destroyed");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Servlet UserServlet at " +
                    request.getContextPath() + "</h2>");
            out.println("<h2>Now: " + new Date() + "</h2>");
            out.println("<h2>BeanValidationUser: " + jpaUser.getName() + "/" +
                    jpaUser.getEmail() + "</h2>");
            out.println("</body>");
            out.println("</html>");
        }
         */

        request.getRequestDispatcher("/jpaUser.jsp")
                .forward(request, response);
        System.out.println("Redirected to jpaUser.jsp");
    }
}
