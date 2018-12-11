package sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sample {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Sample() {
    }

    public Sample(String name) {
        this.name = name;
    }
}
