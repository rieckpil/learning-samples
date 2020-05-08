package com.serverless;

import java.util.Set;

public class Person {

  private String name;
  private Set<String> hobbies;
  private Integer age;

  public Person(String name, Set<String> hobbies, Integer age) {
    this.name = name;
    this.hobbies = hobbies;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getHobbies() {
    return hobbies;
  }

  public void setHobbies(Set<String> hobbies) {
    this.hobbies = hobbies;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
