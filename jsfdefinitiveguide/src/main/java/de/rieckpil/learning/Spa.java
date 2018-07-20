package de.rieckpil.learning;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class Spa implements Serializable {
    
    private String page;

    @PostConstruct
    public void init() {
        page = "page1";
    }

    public void set(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}