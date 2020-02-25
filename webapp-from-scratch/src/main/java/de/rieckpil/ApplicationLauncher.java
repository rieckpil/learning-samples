package de.rieckpil;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.getConnector();
    Context ctx = tomcat.addContext("", null);
    Wrapper servlet = Tomcat.addServlet(ctx, "myServlet", new MyServlet());
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/");
    tomcat.start();
  }
}
