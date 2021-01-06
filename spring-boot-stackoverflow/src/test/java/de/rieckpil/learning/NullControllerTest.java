package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NullController.class)
class NullControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void test() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/null"))
      .andExpect(status().isOk());
  }

}
