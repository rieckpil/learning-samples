package de.rieckpil.learning;

import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
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
      InOrder inOrder = Mockito.inOrder(mockedStatic);
      inOrder.verify(mockedStatic).verify(() -> someService.doRequest());
    }
  }

  @Test
  void testBar() {

    MyInterface mocked = mock(MyInterface.class);

    // when(myInterface.findAll(anyList(), ArgumentMatchers.eq(1), ArgumentMatchers.eq(1L))).thenReturn("bar");

    try (MockedStatic<LocalDateTime> mockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
      someService.doBar();
      InOrder inOrder = Mockito.inOrder(mockedStatic);

      inOrder.verify(mockedStatic).verify(() -> LocalDateTime.now());
      inOrder.verify(mockedStatic).verify(() -> LocalDateTime.of(2020, 12, 31, 12, 0));
    }
  }

  @Test
  void testEqual() {

    List<Integer> one = Arrays.asList(1,2,3);
    List<Integer> two = Arrays.asList(1,2,3);

    System.out.println(one.equals(two));
  }
}
