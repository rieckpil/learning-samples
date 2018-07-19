package de.rieckpil.learning;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@RequestScoped
public class Login {

    private String password;
    private String email;
    private double price = 88.90;

    public String submit() {

        if (Math.random() < 0.5) {
            return "/hello.xhtml?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message1 = new FacesMessage("Authentication failed");
            FacesMessage message2 = new FacesMessage("Authentication failed not xD  ");
            context.addMessage("login:submit", message1);
            context.addMessage(null, message2);
            return null;
        }

    }

    public void onChangeLoginForm(AjaxBehaviorEvent event) {
        System.out.println(event.getComponent().toString());
        System.out.println("someone is typing ...");

        if(Math.random() < 0.5) {
            String outcome = "/hello?faces-redirect=true";
            FacesContext context = FacesContext.getCurrentInstance();
            Application application = context.getApplication();
            NavigationHandler handler = application.getNavigationHandler();
            handler.handleNavigation(context, null, outcome);
        }

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
