package de.rieckpil.learning

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class PersonProvider {

  fun getPersons() = listOf(
    Person("Duke", LocalDate.MIN, UUID.randomUUID()),
    Person("Mike", LocalDate.MAX, UUID.randomUUID()))

}

data class Person(
  val fullName: String,
  @JsonFormat(pattern = "dd-MM-YYYY") val dob: LocalDate,
  @JsonIgnore val id: UUID
)
