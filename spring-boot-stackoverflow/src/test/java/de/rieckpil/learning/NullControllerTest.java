package de.rieckpil.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NullController.class)
class NullControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired(required = false)
  private ProtobufController protobufController;

  @Autowired(required = false)
  private NullController nullController;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Test
  void test() throws Exception {

    assertNull(protobufController);
    assertNotNull(nullController);

    assertNotNull(webApplicationContext.getBean(NullController.class));
    assertThrows(NoSuchBeanDefinitionException.class, () -> webApplicationContext.getBean(ProtobufController.class));

    this.mockMvc.perform(MockMvcRequestBuilders.get("/null"))
      .andExpect(status().isOk());
  }

}
