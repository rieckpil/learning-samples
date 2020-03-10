package de.rieckpil.learning

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(MyController::class)
internal class MyControllerTests(@Autowired private val mockMvc: MockMvc) {

  @Test
  fun testExample() {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/endpoint")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andExpect(content().json("""
        {
         "data": "Some data"
        }
      """.trimIndent()))
  }
}
