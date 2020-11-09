package de.rieckpil.learning;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProtobufController.class)
class ProtobufControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Disabled
  void test() throws Exception {
    this.mockMvc
      .perform(post("/protobuf").contentType("application/x-protobuf"))
      .andExpect(status().isOk());
  }

}
