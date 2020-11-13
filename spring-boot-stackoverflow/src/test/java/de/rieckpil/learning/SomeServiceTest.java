package de.rieckpil.learning;

import kong.unirest.HttpRequestWithBody;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class SomeServiceTest {

  @Mock
  private MyInterface myInterface;

  @InjectMocks
  private SomeService someService;

  @TestConfiguration
  static class TestConfig {

    @Bean
    public MyInterface myInterface() {

      /**
       * Long version of implementing your interface:
       *
       *    return new MyInterface() {
       *         @Override
       *         public String getName() {
       *           return null;
       *         }
       *       };
       */

      // shorter ;-)
      return () -> "Hello World!";
    }
  }

  @Test
  void test() {
    SomeService someService = new SomeService(myInterface, null);
    System.out.println(someService.doFoo());
  }

  @Test
  void testRequest() {
    try (MockedStatic<Unirest> mockedStatic = Mockito.mockStatic(Unirest.class)) {
      someService.doRequest();
    }
  }
}
