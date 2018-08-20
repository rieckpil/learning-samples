package de.rieckpil.learning.products.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    public JsonObject toJson() {
        JsonObject result = Json.createObjectBuilder().add("id", this.id).add("name", this.name).add("price",
                this.price).build();

        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
