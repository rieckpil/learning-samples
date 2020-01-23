package de.rieckpil.learning.junit5examples;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class MyExtension implements ParameterResolver {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    System.out.println(extensionContext);
    return parameterContext.getParameter().getType().isAssignableFrom(String.class);
  }

  @Override
  public String resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    System.out.println(extensionContext);
    System.out.println(parameterContext.getParameter().getName());
    return "Hello World";
  }
}
