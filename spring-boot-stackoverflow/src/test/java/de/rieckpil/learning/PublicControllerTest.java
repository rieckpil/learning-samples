package de.rieckpil.learning;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PublicController.class)
@Import(PublicControllerTest.TestConfig.class)
class PublicControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @TestConfiguration
  static class TestConfig {

    @Bean
    public EntityManager entityManager() {
      return mock(EntityManager.class);
    }

    @Bean
    public MeterRegistry meterRegistry() {
      return new SimpleMeterRegistry();
    }

  }

  @Test
  public void testMe() throws Exception {
    this.mockMvc
      .perform(get("/public"))
      .andExpect(status().isOk());
  }

  @Test
  public void testData() throws Exception {
    this.mockMvc
      .perform(get("/public/data"))
      .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser("duke")
  public void testDataSuccess() throws Exception {
    this.mockMvc
      .perform(get("/public/data"))
      .andExpect(status().isOk());
  }
}
