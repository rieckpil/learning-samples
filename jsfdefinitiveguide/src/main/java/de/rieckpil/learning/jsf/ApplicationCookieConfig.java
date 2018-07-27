package de.rieckpil.learning.jsf;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationCookieConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println(FacesContext.getCurrentInstance().getApplication().getProjectStage().name());

        if (FacesContext.getCurrentInstance().getApplication().getProjectStage().name().equals("Development")) {

            System.out.println("### DISABLING SECURE COOKIE DUE TO LOCAL DEVELOPMENT");

            sce.getServletContext().getSessionCookieConfig().setSecure(false);
        }

    }
}
