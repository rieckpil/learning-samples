package de.rieckpl.learning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SampleServiceTest {

  @Mock
  OtherService otherService;

  @InjectMocks
  SampleService sampleService;

  @Test
  void testMe() {
    when(otherService.doFoo()).thenReturn("Hello World");

    assertEquals("Hello World", sampleService.getData());
  }

}
