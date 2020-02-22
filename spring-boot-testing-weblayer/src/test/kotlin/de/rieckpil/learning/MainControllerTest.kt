package de.rieckpil.learning

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import java.time.LocalDate
import java.util.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@WebMvcTest(MainController::class)
class MainControllerTest {

  @Autowired
  lateinit var mockMvc: MockMvc

  @MockBean
  lateinit var personProvider: PersonProvider

  @Test
  fun testMessage() {
    this.mockMvc.perform(get("/api")).andExpect {
      assertEquals(200, it.response.status)
      assertEquals("Hello World!", it.response.contentAsString)
    }
  }

  @Test
  fun testData() {
    `when`(personProvider.getPersons()).thenReturn(listOf(
      Person("Duke", LocalDate.MIN, UUID.randomUUID()),
      Person("Mike", LocalDate.MAX, UUID.randomUUID())))

    this.mockMvc.perform(get("/data")).andExpect {
      assertEquals(200, it.response.status)
      jsonPath("$.size", `is`(2))
    }.andDo(print())
  }

}
