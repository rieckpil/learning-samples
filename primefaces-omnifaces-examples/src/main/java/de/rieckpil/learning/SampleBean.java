package de.rieckpil.learning;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.rieckpil.learning.entity.PhoneNumber;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class SampleBean {

    @Inject
    @ConfigProperty(name = "message")
    private String message;

    private PhoneNumber number;
    private String postfix;

    public void hello() {
        Messages.addGlobalInfo("Hello {0}! How are you {1} ? | {2}",
                Faces.getRequestParameter("product"),
                Faces.getRequestParameter("contact"),
                this.postfix);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PhoneNumber getNumber() {
        return number;
    }

    public void setNumber(PhoneNumber number) {
        this.number = number;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
}
