package de.rieckpil.learning;

import java.io.IOException;
import java.io.InputStream;

public class Loading {

  public static void main(String[] args) throws IOException {
    Thread currentThread = Thread.currentThread();
    ClassLoader contextClassLoader = currentThread.getContextClassLoader();
    InputStream propFile = contextClassLoader.getResourceAsStream("application.properties");
    System.out.println(propFile);
    byte[] bytes = propFile.readAllBytes();
    System.out.println(bytes.length);
  }
}
