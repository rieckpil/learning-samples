package de.rieckpil.learning;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class PropertyExtension implements BeforeAllCallback {

  @Override
  public void beforeAll(ExtensionContext context) {
    System.out.println("Setting system property");
    System.setProperty("APPENDER_NAME", "duke");
  }

}
