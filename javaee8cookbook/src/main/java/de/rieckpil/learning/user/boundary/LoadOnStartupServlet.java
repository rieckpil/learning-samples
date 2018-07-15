package de.rieckpil.learning.user.boundary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "LoadOnStartupServlet", urlPatterns = {"/LoadOnStartupServlet"}, loadOnStartup = 1)
public class LoadOnStartupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("*******SERVLET LOADED WITH SERVER's STARTUP*******");
    }

}