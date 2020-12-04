package de.rieckpil.learning.user.web;

import de.rieckpil.learning.user.*;


public class EditUserFormData extends CreateUserFormData {

  private String id;
  private long version;

  public static EditUserFormData fromUser(User user) {
    EditUserFormData result = new EditUserFormData();
    result.setId(user.getId().asString());
    result.setVersion(user.getVersion());
    result.setFirstName(user.getUserName().getFirstName());
    result.setLastName(user.getUserName().getLastName());
    result.setGender(user.getGender());
    result.setBirthday(user.getBirthday());
    result.setEmail(user.getEmail().asString());
    result.setPhoneNumber(user.getPhoneNumber().asString());
    return result;
  }

  public EditUsersParameters toParameters() {
    return new EditUsersParameters(
      new UserName(getFirstName(), getLastName()),
      getGender(),
      getBirthday(),
      new Email(getEmail()),
      new PhoneNumber(getPhoneNumber()),
      version);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }
}

