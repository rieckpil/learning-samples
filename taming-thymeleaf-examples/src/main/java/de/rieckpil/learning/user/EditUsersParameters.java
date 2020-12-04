package de.rieckpil.learning.user;

import java.time.LocalDate;

public class EditUsersParameters extends CreateUserParameters{

  private final long version;

  public EditUsersParameters(UserName userName, Gender gender, LocalDate birthday, Email email, PhoneNumber phoneNumber, long version) {
    super(userName, gender, birthday, email, phoneNumber);
    this.version = version;
  }

  public long getVersion() {
    return version;
  }

  public void update(User user) {
    user.setUserName(getUserName());
    user.setGender(getGender());
    user.setBirthday(getBirthday());
    user.setEmail(getEmail());
    user.setPhoneNumber(getPhoneNumber());
  }
}
