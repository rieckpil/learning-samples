package de.rieckpil.learning.springboot2book.entities;

public class Person {

    private long id;
    private String lastName;
    private int age;

    public Person() {
    }

    public Person(long id, String lastName, int age) {
        this.id = id;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
