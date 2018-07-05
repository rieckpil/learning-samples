package de.rieckpil.learning.jasperintro;

public class DataBean {

    private String name;
    private String country;

    public DataBean(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public DataBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
