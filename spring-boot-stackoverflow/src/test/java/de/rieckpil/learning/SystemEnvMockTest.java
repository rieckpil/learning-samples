package de.rieckpil.learning;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SystemEnvMockTest {

  @Test
  @Disabled
  void mockSystemEnv() {
    try (MockedStatic<System> mockedSystem = Mockito.mockStatic(System.class)) {
      mockedSystem.when(System::getenv).thenReturn("DUKE_42");
      System.out.println(System.getenv());
    }
  }
}
