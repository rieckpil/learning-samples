package de.rieckpil.learning.user.web;

import de.rieckpil.learning.infrastructure.ValidationGroupTwo;
import de.rieckpil.learning.user.CreateUserParameters;
import de.rieckpil.learning.user.PhoneNumber;
import de.rieckpil.learning.user.UserName;

import javax.validation.constraints.NotBlank;

@PasswordsMatch(groups = ValidationGroupTwo.class)
public class CreateUserFormData extends AbstractUserFormData {

  @NotBlank
  private String password;

  @NotBlank
  private String passwordRepeated;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordRepeated() {
    return passwordRepeated;
  }

  public void setPasswordRepeated(String passwordRepeated) {
    this.passwordRepeated = passwordRepeated;
  }

  public CreateUserParameters toParameters() {
    return new CreateUserParameters(new UserName(getFirstName(), getLastName()),
      password,
      getGender(),
      getBirthday(),
      new de.rieckpil.learning.user.Email(getEmail()),
      new PhoneNumber(getPhoneNumber()));
  }
}
