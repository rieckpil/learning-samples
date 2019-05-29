package de.rieckpil.learning.java;

public class JavaWorker {

    private final String firstName;

    public JavaWorker(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public static void main(String[] args) {
        String firstName = "John";
        var worker = new JavaWorker(firstName);
        System.out.println(worker.getFirstName());
    }
}
