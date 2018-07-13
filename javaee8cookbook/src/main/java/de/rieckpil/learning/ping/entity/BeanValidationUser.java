package de.rieckpil.learning.ping.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class BeanValidationUser {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotEmpty
    private List<@PositiveOrZero Integer> profileId;

    public BeanValidationUser(String name, String email, List<Integer> profileId) {
        this.name = name;
        this.email = email;
        this.profileId = profileId;
    }
}
