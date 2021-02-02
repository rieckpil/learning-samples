package de.rieckpil.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TestControllerTest {

  @Mock
  private SomeService someService;

  @InjectMocks
  private TestController testController;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
  }

  @Test
  void testEndpoint() throws Exception {

    Mockito.when(someService.doFoo()).thenReturn("42");

    this.mockMvc
      .perform(MockMvcRequestBuilders.get("/api/tests"))
      .andExpect(status().isOk());
  }
}
