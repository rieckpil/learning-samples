package de.rieckpil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletInitializr implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletRegistration.Dynamic dynamic = sce.getServletContext().addServlet("myServlet", MyServlet.class);
    dynamic.addMapping("/");
  }
}
