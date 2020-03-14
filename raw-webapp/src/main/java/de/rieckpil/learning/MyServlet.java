package de.rieckpil.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");
    try (PrintWriter writer = resp.getWriter()) {

      writer.println("<!DOCTYPE html><html>");
      writer.println("<head>");
      writer.println("<meta charset=\"UTF-8\" />");
      writer.println("<title>Sample Application Servlet Page</title>");
      writer.println("</head>");
      writer.println("<body>");


      writer.println("<div style=\"float: left; padding: 10px;\">");
      writer.println("<img src=\"images/tomcat.gif\" alt=\"\" />");
      writer.println("</div>");
      writer.println("<h1>Sample Application Servlet</h1>");
      writer.println("<p>");
      writer.println("This is the output of a servlet that is part of");
      writer.println("the Hello, World application.");
      writer.println("</p>");

      writer.println("</body>");
      writer.println("</html>");
    }
  }
}
