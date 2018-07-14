package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;
import de.rieckpil.learning.user.entity.Message;
import de.rieckpil.learning.user.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;

@ViewScoped
@Named
public class UserView implements Serializable {

    private String json;

    public void loadUsers() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI.create
                ("http://localhost:9080/"));

        JpaUser[] response = target.path("resources/user/getJpaUser")
                        .request()
                        .accept(MediaType.APPLICATION_JSON)
                        .get(JpaUser[].class);

        for (JpaUser jpaUser : response) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("userFromBean: " + jpaUser));
        }

    }

    public void loadJson() {

        long now = System.currentTimeMillis();

        Message msg = new Message(now, "Msg" + now, "msg" + now + "@eldermoraes.com", Math.random(), LocalDate.now());

        Jsonb jb = JsonbBuilder.create();
        json = jb.toJson(msg);
        try {
            jb.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}