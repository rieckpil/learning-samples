package de.rieckpl.learning;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PublicControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebTestClient webTestClient;

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
  @Disabled
  public void testMultipart() throws Exception {

    byte[] file = new byte[10];
    MockMultipartFile filePart = new MockMultipartFile(
      "file", "file.jpg", "image/jpeg", file);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/loadfile")
      .file(filePart)
      .param("json", "json"))
      .andExpect(status().isOk())
      .andReturn();
  }

  @Test
  @WithMockUser("duke")
  public void testDataSuccess() throws Exception {
    this.mockMvc
      .perform(get("/public/data"))
      .andExpect(status().isOk());
  }

  @Test
  @WithMockUser("duke")
  public void testDataSuccessTwo() throws Exception {
    // this.webTestClient.get().uri("/public/data").exchange().expectStatus().isOk();
  }
}
