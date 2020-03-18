package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SampleController.class)
class SampleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testAccess() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/sample"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("text/plain;charset=UTF-8"))
      .andExpect(content().string("Hello World"));
  }

  @Test
  @WithMockUser(roles = {"USER", "ADMIN"})
  public void testSecureAccess() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/sample/secure"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("text/plain;charset=UTF-8"))
      .andExpect(content().string("Secure Hello World"));
  }

}
