package de.rieckpil.learning;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SampleServiceTest {

  @Mock
  OtherService otherService;

  @InjectMocks
  SampleService sampleService;

  @Test
  void bddTest() {
    doAnswer(invocationOnMock -> "duuke").when(otherService).doFoo();
    assertEquals("duuke", sampleService.getData());
  }

  @Test
  void testMe() {
    when(otherService.doFoo()).thenReturn("Hello World");
    when(otherService.doBar(any(), ArgumentMatchers.any())).thenReturn("Hello World");

    assertEquals("Hello World", sampleService.getData());

    String result = "{\"age\":\"42\", \"name\": \"duke\", \"tags\":[\"java\", \"jdk\"]}";

    assertEquals(2, JsonPath.parse(result).read("$.tags.length()", Long.class));
    assertEquals("duke", JsonPath.parse(result).read("$.name", String.class));
    // assertEquals("your value", JsonPath.parse(result).read("$.my.nested.values[0].name", String.class));
  }

}
