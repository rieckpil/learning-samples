package de.rieckpil.learning.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class BillingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
