package com.serverless;

public class Person {

  private String name;
  private Boolean isSecured;
  private Integer age;

  public Person(String name, Boolean isSecured, Integer age) {
    this.name = name;
    this.isSecured = isSecured;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getSecured() {
    return isSecured;
  }

  public void setSecured(Boolean secured) {
    isSecured = secured;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
