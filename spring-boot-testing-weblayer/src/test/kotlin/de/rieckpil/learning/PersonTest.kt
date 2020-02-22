package de.rieckpil.learning

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.time.LocalDate
import java.util.*


@JsonTest
internal class PersonTest {

  @Autowired
  private lateinit var json: JacksonTester<Person>

  @Test
  fun testSerialize() {
    val person = Person("Duke", LocalDate.now(), UUID.randomUUID())
    val jsonOutput = json.write(person)
    println(jsonOutput)

    assertThat(jsonOutput).hasEmptyJsonPathValue("@.id")
    assertThat(jsonOutput).hasJsonPathValue("@.fullName")
    assertThat(jsonOutput).hasJsonPathValue("@.dob")
  }
}
