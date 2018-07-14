package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;
import de.rieckpil.learning.user.entity.Message;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        JsonbConfig config = new JsonbConfig().withPropertyNamingStrategy(PropertyNamingStrategy
                .LOWER_CASE_WITH_UNDERSCORES);

        Jsonb jb = JsonbBuilder.create(config);
        json = jb.toJson(msg);
        try {
            jb.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadDogs() {


        Dog dog1 = new Dog("Frank1", 21, false);
        Dog dog2 = new Dog("Frank2", 112, false);
        Dog dog3 = new Dog("Frank3", 1212, true);
        Dog dog4 = new Dog("Frank4", 14, true);
        Dog dog5 = new Dog("Fran5", 122, false);

        List<Dog> dogList = new ArrayList<>();
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
        dogList.add(dog4);
        dogList.add(dog5);

        JsonbConfig config = new JsonbConfig().withFormatting(true);

        Jsonb jsonb = JsonbBuilder.create(config);

        System.out.println(jsonb.toJson(dogList));

        this.json = jsonb.toJson(dogList);

        String dogListAsJson = "[{\"age\":21,\"bitable\":false,\"name\":\"Frank1\"},{\"age\":112,\"bitable\":false," +
                "\"name\":\"Frank2\"},{\"age\":1212,\"bitable\":true,\"name\":\"Frank3\"},{\"age\":14," +
                "\"bitable\":true,\"name\":\"Frank4\"},{\"age\":122,\"bitable\":false,\"name\":\"Fran5\"}]\n";

        List listOfDogs = jsonb.fromJson(dogListAsJson, ArrayList.class);

        System.out.println(listOfDogs.size());


    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public class Dog {
        private String name;
        private int age;
        private boolean bitable;

        public Dog(String name, int age, boolean bitable) {
            this.name = name;
            this.age = age;
            this.bitable = bitable;
        }

        public Dog() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isBitable() {
            return bitable;
        }

        public void setBitable(boolean bitable) {
            this.bitable = bitable;
        }
    }

}